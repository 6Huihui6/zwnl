package com.zwnl.user.service.impl;

import com.zwnl.user.domain.po.Users;
import com.zwnl.user.mapper.UsersMapper;
import com.zwnl.user.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hui
 * @since 2024-09-09
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
