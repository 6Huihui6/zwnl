package com.zwnl.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwnl.common.domain.dto.LoginUserDTO;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.common.enums.AppHttpCodeEnum;
import com.zwnl.common.exceptions.BadRequestException;
import com.zwnl.common.exceptions.ForbiddenException;
import com.zwnl.common.exceptions.LoginFailedException;
import com.zwnl.common.utils.*;
import com.zwnl.model.user.dtos.LoginFormDTO;
import com.zwnl.model.user.dtos.PhoneLoginDTO;
import com.zwnl.model.user.dtos.UserDTO;
import com.zwnl.model.user.dtos.UserLoginDTO;
import com.zwnl.model.user.enums.UserStatus;
import com.zwnl.model.user.pos.UserDetail;
import com.zwnl.model.user.pos.Users;
import com.zwnl.model.user.vos.UserDetailVO;
import com.zwnl.user.constant.MessageConstant;
import com.zwnl.user.mapper.UsersMapper;
import com.zwnl.user.properties.WeChatProperties;
import com.zwnl.user.service.IUserDetailService;
import com.zwnl.user.service.IUsersService;
import com.zwnl.utils.thread.AppThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.zwnl.model.user.enums.UserRole.EMPLOYEE;
import static com.zwnl.user.constant.UserConstants.*;
import static com.zwnl.user.constant.UserErrorInfo.Msg.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-09
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    //微信服务接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";
    private final PasswordEncoder passwordEncoder;
    private final WeChatProperties weChatProperties;
    private final IUserDetailService detailService;

    /**
     * 微信登录
     *
     * @param userLoginDTO
     * @return
     */
    @Override
    public Users wxLogin(UserLoginDTO userLoginDTO) {
        String openid = getOpenid(userLoginDTO.getCode());

        //判断openid是否为空，如果为空表示登录失败，抛出业务异常
        if(openid == null){
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //判断当前用户是否为新用户
//        Users user = userMapper.getByOpenid(openid);
        Users user = this.lambdaQuery().eq(Users::getOpenid, openid).one();

        //如果是新用户，自动完成注册
        if(user == null){
            user = Users.builder()
                    .openid(openid)
                    .createdTime(LocalDateTime.now())
                    .build();
            this.save(user);
        }
        //返回这个用户对象
        return user;
    }

    /**
     *账号密码注册
     *
     * @param users
     */
    @Override
    public void register(Users users) {
        //判断当前用户是否为新用户
        Users user = this.lambdaQuery().eq(Users::getPhone, users.getPhone()).one();
        if(user != null){
            throw new LoginFailedException(MessageConstant.ALREADY_EXISTS);
        }
        //保存用户信息
        if (users.getPhone()!=null&&users.getPassword()!=null){
            users.setCreatedTime(LocalDateTime.now());
            save(users);
        }
    }

    /**
     * 获取用户详情
     *
     * @return
     */
    @Override
    public UserDetailVO myInfo() {
        // 1.获取登录用户id
        Long userId = UserContext.getUser();
//        Users user = AppThreadLocalUtil.getUser();
//        Long userId = Long.valueOf(user.getUserId());
        if (userId == null) {
            return null;
        }
        // 2.查询用户
        UserDetail userDetail = detailService.queryByUserId(userId);
        AssertUtils.isNotNull(userDetail,USER_ID_NOT_EXISTS);
        // 3.封装vo
        // 3.1.基本信息
        UserDetailVO vo = BeanUtils.toBean(userDetail, UserDetailVO.class);
        return vo;
    }

    /**
     * 更新用户信息
     * @param userDTO
     */
    @Override
    public void updateUser(UserDTO userDTO) {
        // 1.如果传递了手机号，则修改手机号
        String cellphone = userDTO.getPhone();
        if(StringUtils.isNotBlank(cellphone)){
            Users user = new Users();
            user.setUserId(userDTO.getUserId());
            user.setPhone(cellphone);
            user.setUsername(cellphone);
            updateById(user);
        }
        // 2.修改详情
        UserDetail detail = BeanUtils.toBean(userDTO, UserDetail.class);
        detail.setType(null);
        detailService.updateById(detail);
    }

    /**
     * 手机号登录
     *
     * @param phoneLoginDTO
     * @return
     */
    @Override
    public ResponseResult phoneLogin(PhoneLoginDTO phoneLoginDTO) {
        // 1.根据手机号查询用户
        Users user = this.lambdaQuery().eq(Users::getPhone, phoneLoginDTO.getPhone()).one();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 2.校验密码
        if (!user.getPassword().equals(phoneLoginDTO.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR, MessageConstant.PASSWORD_ERROR);
        }
        // 3.登录成功，设置用户信息到ThreadLocal
        AppThreadLocalUtil.setUser(user);
        // 4.返回登录成功结果
        return ResponseResult.okResult(200, MessageConstant.LOGIN_SUCCESS);
    }

    /**
     * 登录
     *
     * @param loginDTO
     * @param isStaff
     * @return
     */
    @Override
    public LoginUserDTO queryUserDetail(LoginFormDTO loginDTO, boolean isStaff) {
        log.info(passwordEncoder.encode(DEFAULT_PASSWORD));
        // 1.判断登录方式
        Integer type = loginDTO.getType();
        Users user = null;
        // 2.用户名和密码登录
        if (type == 1) {
            user = loginByPw(loginDTO);
        }
        // 3.验证码登录
        if (type == 2) {
            user = loginByVerifyCode(loginDTO.getCellPhone(), loginDTO.getPassword());
        }
        // 4.错误的登录方式
        if (user == null) {
            throw new BadRequestException(ILLEGAL_LOGIN_TYPE);
        }
        // 5.判断用户类型与登录方式是否匹配
        if (isStaff ^ user.getRole() != EMPLOYEE) {
            throw new BadRequestException(isStaff ? "非管理端用户" : "非学生端用户");
        }
        // 6.封装返回
        LoginUserDTO userDTO = new LoginUserDTO();
        userDTO.setUserId(Long.valueOf(user.getUserId()));
        userDTO.setRoleId(handleRoleId(user));
        return userDTO;
    }
    public Users loginByVerifyCode(String phone, String code) {
        // 1.校验验证码
//        codeService.verifyCode(phone, code);
        // 2.根据手机号查询
        Users user = lambdaQuery().eq(Users::getPhone, phone).one();
        if (user == null) {
            throw new BadRequestException(PHONE_NOT_EXISTS);
        }
        // 3.校验是否禁用
        if (user.getStatus() == UserStatus.BANNED) {
            throw new ForbiddenException(USER_FROZEN);
        }
        return user;
    }
    public Users loginByPw(LoginFormDTO loginDTO) {
        // 1.数据校验
        String username = loginDTO.getUsername();
        String cellPhone = loginDTO.getCellPhone();
        if (StrUtil.isBlank(username) && StrUtil.isBlank(cellPhone)) {
            throw new BadRequestException(INVALID_UN);
        }
        // 2.根据用户名或手机号查询
        Users user = lambdaQuery()
                .eq(StrUtil.isNotBlank(username), Users::getUsername, username)
                .eq(StrUtil.isNotBlank(cellPhone), Users::getPhone, cellPhone)
                .one();
        AssertUtils.isNotNull(user, INVALID_UN_OR_PW);
        // 3.校验是否禁用
        if (user.getStatus() == UserStatus.BANNED) {
            throw new ForbiddenException(USER_FROZEN);
        }
        // 4.校验密码
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new BadRequestException(INVALID_UN_OR_PW);
        }

        return user;
    }

    private Long handleRoleId(Users user) {
        Long roleId = 0L;
        switch (user.getRole()) {
            case EMPLOYEE:
                roleId = EMPLOYEE_ROLE_ID;
                break;
            case EMPLOYER:
                roleId = EMPLOYER_ROLE_ID;
                break;
//            case STAFF:
//                UserDetail detail = detailService.getById(user.getUserId());
//                roleId = detail.getRoleId();
//                break;
        }
        return roleId;
    }


    /**
     * 调用微信接口服务，获取微信用户的openid
     * @param code
     * @return
     */
    private String getOpenid(String code){
        //调用微信接口服务，获得当前微信用户的openid
        Map<String, String> map = new HashMap<>();
        map.put("appid",weChatProperties.getAppid());
        map.put("secret",weChatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String json = HttpClientUtil.doGet(WX_LOGIN, map);

        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
