package com.zwnl.user.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.zwnl.model.user.dtos.UserLoginDTO;
import com.zwnl.model.user.pos.Users;

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
}
