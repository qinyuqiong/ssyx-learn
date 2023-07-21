package com.atguigu.ssyx.controller;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.utils.MD5;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.service.AdminService;
import com.atguigu.ssyx.service.RoleService;
import com.atguigu.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 17273
 * @date 2023/7/16
 */
@Api(tags = "用户管理")
@RestController
@CrossOrigin
@RequestMapping("admin/acl/user")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取用户所有角色")
    @GetMapping("/toAssign/{adminId}")
    public Result<Map<String, Object>> toAssign(@PathVariable Long adminId) {
        Map<String, Object> map = roleService.findRoleByAdminId(adminId);
        return Result.ok(map);
    }

    @ApiOperation("分配角色")
    @PostMapping("/doAssign")
    public Result<Void> doAssign(@RequestParam Long adminId, @RequestParam List<Long> roleId) {
        roleService.saveAdminRole(adminId, roleId);
        return Result.ok(null);
    }

    @ApiOperation("分页查询")
    @GetMapping("/{page}/{limit}")
    public Result<IPage<Admin>> getPageList(@PathVariable Long page, @PathVariable Long limit, AdminQueryVo adminQueryVo) {
        Page<Admin> pageParam = new Page<>(page, limit);
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotEmpty(adminQueryVo.getName())) {
            queryWrapper.like(Admin::getName, adminQueryVo.getName());
        }
        if (StringUtil.isNotEmpty(adminQueryVo.getUsername())) {
            queryWrapper.like(Admin::getUsername, adminQueryVo.getUsername());
        }
        IPage<Admin> adminPage = adminService.page(pageParam, queryWrapper);
        return Result.ok(adminPage);
    }

    @ApiOperation("标准用户添加")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody Admin admin) {
        String password = admin.getPassword();
        String encrypt = MD5.encrypt(password);
        admin.setPassword(encrypt);
        boolean save = adminService.save(admin);
        return save ? Result.ok(null) : Result.fail(null);
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public Result<Void> update(@RequestBody Admin admin) {
        boolean update = adminService.updateById(admin);
        return update ? Result.ok(null) : Result.fail(null);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取用户")
    public Result<Admin> get(@PathVariable Long id) {
        Admin user = adminService.getById(id);
        return Result.ok(user);
    }


    @DeleteMapping("remove/{id}")
    @ApiOperation("删除用户")
    public Result<Void> delete(@PathVariable Long id) {

        adminService.removeById(id);
        return Result.ok(null);
    }

    @DeleteMapping("/batchRemove")
    @ApiOperation("所有用户删除")
    public Result<Void> batchRemove(@RequestBody List<Long> ids) {
        adminService.removeByIds(ids);
        return Result.ok(null);
    }
}
