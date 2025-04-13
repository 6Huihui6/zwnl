package com.zwnl.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zwnl.auth.mapper.RoleMapper;
import com.zwnl.auth.service.IRoleMenuService;
import com.zwnl.auth.service.IRolePrivilegeService;
import com.zwnl.auth.service.IRoleService;
import com.zwnl.auth.util.PrivilegeCache;
import com.zwnl.model.auth.po.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2022-06-16
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    private final IRoleMenuService roleMenuService;
    private final IRolePrivilegeService rolePrivilegeService;
    private final PrivilegeCache privilegeCache;

    @Override
    public boolean exists(Long roleId) {
        Integer count = Math.toIntExact(lambdaQuery().eq(Role::getId, roleId).count());
        return count > 0;
    }

    @Override
    public boolean exists(List<Long> roleIds) {
        Integer count = Math.toIntExact(lambdaQuery().in(Role::getId, roleIds).count());
        return count != roleIds.size();
    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        // 1.删除角色
        removeById(id);
        // 2.删除角色与权限的关联信息
        roleMenuService.removeByRoleId(id);
        rolePrivilegeService.removeByRoleId(id);
        // 3.清理缓存
        privilegeCache.removeCacheByRoleId(id);
    }
}
