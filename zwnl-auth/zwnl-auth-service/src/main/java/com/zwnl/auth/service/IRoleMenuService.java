package com.zwnl.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwnl.model.auth.po.RoleMenu;


import java.util.List;

/**
 * <p>
 * 账户、角色关联表 服务类
 * </p>
 *
 * @author 虎哥
 * @since 2022-06-16
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    void removeByRoleId(Long id);

    void deleteRoleMenus(Long roleId, List<Long> menuIds);
}
