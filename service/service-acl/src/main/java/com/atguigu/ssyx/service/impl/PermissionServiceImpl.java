package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.PermissionMapper;
import com.atguigu.ssyx.model.acl.Permission;
import com.atguigu.ssyx.model.acl.RolePermission;
import com.atguigu.ssyx.service.PermissionService;
import com.atguigu.ssyx.service.RolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 17273
 * @date 2023/7/21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public void recursiveDeletion(Long id) {
        //根据id递归删除
        List<Long> permissionIds = new ArrayList<>();
        getAllowedPermissionIds(id, permissionIds);
        permissionIds.add(id);
        baseMapper.deleteBatchIds(permissionIds);
    }

    @Override
    public void saveParamPermission(Long roleId, List<Long> permissionIds) {
        //先查询角色已经保存的目录
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionService.remove(queryWrapper);
        //重新分配
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (Long permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissions.add(rolePermission);
        }
        rolePermissionService.saveBatch(rolePermissions);
    }

    @Override
    public List<Permission> findPermissionByRoleId(Long roleId) {
        //查询用户有的菜单
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getRoleId, roleId);
        List<RolePermission> list = rolePermissionService.list(queryWrapper);
        List<Long> permissionIds = list.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        //获取所有菜单
        List<Permission> permissionList = baseMapper.selectList(null);
        List<Permission> result = new ArrayList<>(permissionList.size());
        for (Permission permission : permissionList) {
            if (permissionIds.contains(permission.getId())) {
                permission.setSelect(true);
            } else {
                permission.setSelect(false);
            }
            result.add(permission);
        }
        return result;
    }

    /**
     * 获取子菜单id
     *
     * @param permissionId 父菜单id
     */
    private void getAllowedPermissionIds(Long permissionId, List<Long> permissionIds) {
        Permission permission = baseMapper.selectById(permissionId);
        LambdaQueryWrapper<Permission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Permission::getPid, permission.getId());
        List<Permission> permissionList = baseMapper.selectList(queryWrapper);
        permissionList.forEach(p -> {
            permissionIds.add(p.getId());
            //递归
            getAllowedPermissionIds(p.getId(), permissionIds);
        });
    }
}
