package com.atguigu.ssyx.controller;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.service.RoleService;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 17273
 * @date 2023/7/14
 */
@Api(tags = "角色管理")
@RestController
@CrossOrigin
@RequestMapping("admin/acl/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 角色列表条件分页查询
     *
     * @param page        当前页
     * @param limit       每页记录数
     * @param roleQueryVo 查询框框
     * @return Result
     */
    @ApiOperation("角色列表条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result<IPage<Role>> getPageList(@PathVariable Long page, @PathVariable Long limit, RoleQueryVo roleQueryVo) {
        Page<Role> rolePage = new Page<>(page, limit);
        IPage<Role> pageModel = roleService.selectPage(rolePage, roleQueryVo);
        return Result.ok(pageModel);
    }

    /**
     * @param id id
     * @return Result
     */
    @ApiOperation("根据id查询角色")
    @GetMapping("/get/{id}")
    public Result<Role> get(@PathVariable String id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    /**
     * @param role 角色
     * @return Result
     */
    @ApiOperation("保存角色")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody Role role) {
        boolean save = roleService.save(role);
        return save ? Result.ok(null) : Result.fail(null);
    }

    @ApiOperation("根据id更新角色")
    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody Role role) {
        roleService.updateById(role);
        return Result.ok(null);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/remove/{id}")
    public Result<Void> removeById(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("删除多名角色")
    @DeleteMapping("/batchRemove")
    public Result<Void> removeByIds(@RequestBody List<Long> ids) {
        roleService.removeByIds(ids);
        return Result.ok(null);
    }
}
