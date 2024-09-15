package com.zwnl.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwnl.common.exceptions.LoginFailedException;
import com.zwnl.common.utils.AssertUtils;
import com.zwnl.common.utils.BeanUtils;
import com.zwnl.common.utils.HttpClientUtil;
import com.zwnl.common.utils.StringUtils;
import com.zwnl.model.user.dtos.UserDTO;
import com.zwnl.model.user.dtos.UserLoginDTO;
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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.zwnl.user.constant.UserErrorInfo.Msg.USER_ID_NOT_EXISTS;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-09
 */
@Service
@RequiredArgsConstructor
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    //微信服务接口地址
    public static final String WX_LOGIN = "https://api.weixin.qq.com/sns/jscode2session";

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
//        Long userId = UserContext.getUser();
        Users user = AppThreadLocalUtil.getUser();
        Long userId = Long.valueOf(user.getUserId());
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
