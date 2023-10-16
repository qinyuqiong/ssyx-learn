package com.atguigu.ssyx.controller;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Permission;
import com.atguigu.ssyx.service.PermissionService;
import com.atguigu.ssyx.utils.PermissionHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 17273
 * @date 2023/7/21
 */
@Api(tags = "菜单管理")
@RequestMapping("/admin/acl/permission")
@RestController
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation("分配权限")
    @PostMapping("/doAssign")
    public Result<Void> doAssign(@RequestParam Long roleId, @RequestParam List<Long> permissionId) {
        permissionService.saveParamPermission(roleId, permissionId);
        return Result.ok(null);
    }

    @ApiOperation("获取角色菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result<List<Permission>> toAssign(@PathVariable Long roleId) {
        List<Permission> permissionByRoleId = permissionService.findPermissionByRoleId(roleId);
        List<Permission> permissions = PermissionHelper.buildPermission(permissionByRoleId);
        return Result.ok(permissions);
    }

    @ApiOperation("查询所有菜单")
    @GetMapping
    public Result<List<Permission>> getPermissionList() {
        List<Permission> list = permissionService.list();
        List<Permission> permissionList = PermissionHelper.buildPermission(list);
        return Result.ok(permissionList);
    }

    @PostMapping("/save")
    @ApiOperation("添加菜单")
    public Result<Void> save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok(null);
    }

    @PutMapping("/update")
    @ApiOperation("修改菜单")
    public Result<Void> update(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok(null);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除菜单")
    public Result<Void> delete(@PathVariable Long id) {
        //递归删除
        permissionService.recursiveDeletion(id);
        return Result.ok(null);
    }
}
