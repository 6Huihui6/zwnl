package com.zwnl.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.zwnl.common.domain.dto.LoginUserDTO;
import com.zwnl.common.domain.dto.ResponseResult;
import com.zwnl.model.user.dtos.LoginFormDTO;
import com.zwnl.model.user.dtos.PhoneLoginDTO;
import com.zwnl.model.user.dtos.UserDTO;
import com.zwnl.model.user.dtos.UserLoginDTO;
import com.zwnl.model.user.pos.Users;
import com.zwnl.model.user.vos.UserDetailVO;

import javax.validation.Valid;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hui
 * @since 2024-09-09
 */
public interface IUsersService extends IService<Users> {

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    Users wxLogin(UserLoginDTO userLoginDTO);

    /**
     * zhuye注册
     * @param users
     */
    void register(Users users);

    /**
     * 获取用户详情
     * @return
     */
    UserDetailVO myInfo();

    /**
     * 更新用户信息
     * @param userDTO
     */
    void updateUser(UserDTO userDTO);

    /**
     * 手机号登录
     * @param phoneLoginDTO
     * @return
     */
    ResponseResult phoneLogin(PhoneLoginDTO phoneLoginDTO);

    /**
     * 登录
     * @param loginDTO
     * @param isStaff
     * @return
     */
    LoginUserDTO queryUserDetail(@Valid LoginFormDTO loginDTO, boolean isStaff);
}
