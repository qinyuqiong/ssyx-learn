package com.atguigu.ssyx.service;

import com.atguigu.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 17273
 * @date 2023/7/21
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 递归删除id
     *
     * @param id 菜单id
     */
    void recursiveDeletion(Long id);

    void saveParamPermission(Long roleId, List<Long> permissionIds);

    List<Permission> findPermissionByRoleId(Long roleId);
}
