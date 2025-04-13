package com.zwnl.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zwnl.auth.mapper.AccountRoleMapper;
import com.zwnl.auth.service.IAccountRoleService;
import com.zwnl.model.auth.po.AccountRole;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户、角色关联表 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2022-06-16
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole> implements IAccountRoleService {

}
