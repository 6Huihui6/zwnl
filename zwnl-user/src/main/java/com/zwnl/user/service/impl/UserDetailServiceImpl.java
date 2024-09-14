package com.zwnl.user.service.impl;



import com.zwnl.model.user.pos.UserDetail;
import com.zwnl.user.mapper.UserDetailMapper;
import com.zwnl.user.service.IUserDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 教师详情表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-14
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements IUserDetailService {

    /**
     * 根据用户id查询用户详情
     *
     * @param userId
     * @return
     */
    @Override
    public UserDetail queryByUserId(Long userId) {
        UserDetail userDetail = lambdaQuery().eq(UserDetail::getUserId, userId).one();
        return userDetail;
    }

}
