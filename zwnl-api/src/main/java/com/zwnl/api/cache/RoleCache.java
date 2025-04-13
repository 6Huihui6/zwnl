package com.zwnl.api.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.zwnl.api.client.auth.AuthClient;
import com.zwnl.model.user.enums.UserType;
import com.zwnl.model.auth.dto.RoleDTO;
import com.zwnl.model.user.dtos.UserDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleCache {

    private final Cache<Long, RoleDTO> roleCaches;
    private final AuthClient authClient;

    public String getRoleName(Long roleId) {
        RoleDTO roleDTO = roleCaches.get(roleId, authClient::queryRoleById);
        if (roleDTO == null) {
            return null;
        }
        return roleDTO.getName();
    }

//    public String exchangeRoleName(UserDTO u) {
//        if (u == null) {
//            return "--";
//        }
//        if (UserType.STUDENT.equalsValue(u.getType())) {
//            // 学生，直接返回角色名称
//            return u.getName();
//        } else {
//            // 管理员需要拼接角色名称
//            return getRoleName(u.getRoleId()) + "-" + u.getName();
//        }
//    }
}
