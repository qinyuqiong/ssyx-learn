package com.atguigu.ssyx.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.Ware;
import com.atguigu.ssyx.service.WareService;
import com.atguigu.ssyx.vo.product.WareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Api(tags = "仓库管理")
@RestController

@RequestMapping("/admin/sys/ware")
public class WareController {

    @Autowired
    private WareService wareService;

    @ApiOperation("分页查询")
    @GetMapping("/{page}/{limit}")
    public Result<IPage<Ware>> getPageList(@PathVariable Long page, @PathVariable Long limit, WareQueryVo wareQueryVo) {
        Page<Ware> warePage = new Page<>(page, limit);
        IPage<Ware> query = wareService.selectWare(warePage, wareQueryVo);
        return Result.ok(query);
    }

    @ApiOperation("查询所有仓库")
    @GetMapping("/findAllList")
    public Result<List<Ware>> getList() {
        List<Ware> list = wareService.list();
        return Result.ok(list);
    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/remove/{id}")
    public Result<Void> removeById(@PathVariable Long id) {
        wareService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result<Void> removeByIds(@RequestParam("ids") List<Long> ids) {
        wareService.removeByIds(ids);
        return Result.ok(null);
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody Ware ware) {
        wareService.save(ware);
        return Result.ok(null);
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public Result<Void> update(@RequestBody Ware ware) {
        wareService.updateById(ware);
        return Result.ok(null);
    }

    @ApiOperation("根据id获取")
    @GetMapping("/get/{id}")
    public Result<Ware> getById(Long id) {
        Ware ware = wareService.getById(id);
        return Result.ok(ware);
    }
}

