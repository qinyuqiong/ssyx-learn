package com.atguigu.ssyx.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.service.SkuInfoService;
import com.atguigu.ssyx.vo.product.SkuInfoQueryVo;
import com.atguigu.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * sku信息 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */

@RestController
@RequestMapping("/admin/product/skuInfo")
public class SkuInfoController {
    @Autowired
    private SkuInfoService skuInfoService;

    @ApiOperation(value = "获取sku分页列表")
    @GetMapping("{page}/{limit}")
    public Result<IPage<SkuInfo>> index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "skuInfoQueryVo", value = "查询对象")
            SkuInfoQueryVo skuInfoQueryVo) {
        Page<SkuInfo> pageParam = new Page<>(page, limit);
        IPage<SkuInfo> pageModel = skuInfoService.selectPage(pageParam, skuInfoQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "保存sku信息")
    @PostMapping("save")
    public Result<Void> save(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.saveSkuInfo(skuInfoVo);
        return Result.ok(null);
    }

    @ApiOperation("获取sku信息")
    @GetMapping("get/{id}")
    public Result<SkuInfoVo> get(@PathVariable Long id) {
        SkuInfoVo skuInfoVo = skuInfoService.getSkuInfoById(id);
        return Result.ok(skuInfoVo);
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody SkuInfoVo skuInfoVo) {
        skuInfoService.updateSkuInfoById(skuInfoVo);
        return Result.ok(null);
    }

    @ApiOperation("删除")
    @DeleteMapping("/remove/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        skuInfoService.removeSkuInfoById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result<Void> deleteRows(@RequestBody List<Long> ids) {
        skuInfoService.removeSkuInfoByIds(ids);
        return Result.ok(null);
    }

    @ApiOperation("商品上架")
    @GetMapping("/publish/{id}/{status}")
    public Result<Void> publish(@PathVariable Long id, @PathVariable Integer status) {
        skuInfoService.updatePublish(id, status);
        return Result.ok(null);
    }

    @ApiOperation("商品审核")
    @GetMapping("/check/{id}/{status}")
    public Result<Void> check(@PathVariable Long id, @PathVariable Integer status) {
        skuInfoService.updateCheck(id, status);
        return Result.ok(null);
    }

    @ApiOperation("新人专享")
    @GetMapping("/isNewPerson/{id}/{status}")
    public Result<Void> isNewPerson(@PathVariable Long id, @PathVariable Integer status) {
        skuInfoService.updateNewPerson(id, status);
        return Result.ok(null);
    }
}

