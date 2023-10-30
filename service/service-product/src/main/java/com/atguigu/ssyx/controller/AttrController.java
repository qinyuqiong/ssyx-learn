package com.atguigu.ssyx.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.product.Attr;
import com.atguigu.ssyx.service.AttrService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 商品属性 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */

@RestController
@RequestMapping("/admin/product/attr")
public class AttrController {

    @Autowired
    private AttrService attrService;

    @ApiOperation(value = "获取列表")
    @GetMapping("{groupId}")
    public Result<List<Attr>> index(
            @ApiParam(name = "groupId", value = "分组id", required = true)
            @PathVariable Long groupId) {
        return Result.ok(attrService.findByAttrGroupId(groupId));
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result<Attr> get(@PathVariable Long id) {
        Attr attr = attrService.getById(id);
        return Result.ok(attr);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result<Void> save(@RequestBody Attr attr) {
        attrService.save(attr);
        return Result.ok(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result<Void> updateById(@RequestBody Attr attr) {
        attrService.updateById(attr);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result<Void> remove(@PathVariable Long id) {
        attrService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result<Void> batchRemove(@RequestBody List<Long> idList) {
        attrService.removeByIds(idList);
        return Result.ok(null);
    }
}

