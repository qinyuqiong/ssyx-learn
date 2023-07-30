package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.PermissionMapper;
import com.atguigu.ssyx.model.acl.Permission;
import com.atguigu.ssyx.service.PermissionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 17273
 * @date 2023/7/21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public void recursiveDeletion(Long id) {
        //根据id递归删除
        List<Long> permissionIds = new ArrayList<>();
        getAllowedPermissionIds(id, permissionIds);
        permissionIds.add(id);
        baseMapper.deleteBatchIds(permissionIds);
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
