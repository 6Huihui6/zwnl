package com.zwnl.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zwnl.model.user.pos.UserDetail;

/**
 * <p>
 * 教师详情表 服务类
 * </p>
 *
 * @author hui
 * @since 2024-09-14
 */
public interface IUserDetailService extends IService<UserDetail> {

    /**
     * 根据用户id查询用户详情
     * @param userId
     * @return
     */
    UserDetail queryByUserId(Long userId);


}
