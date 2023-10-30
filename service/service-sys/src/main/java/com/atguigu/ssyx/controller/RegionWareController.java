package com.atguigu.ssyx.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.service.RegionWareService;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 城市仓库关联表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Api(tags = "地区仓库管理")
@RestController

@RequestMapping("/admin/sys/regionWare")
public class RegionWareController {
    @Autowired
    private RegionWareService regionWareService;

    @ApiOperation("分页查询")
    @GetMapping("/{page}/{limit}")
    public Result<IPage<RegionWare>> index(@ApiParam(name = "page", value = "当前页码", required = true) @PathVariable Long page,
                                           @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable Long limit,
                                           @ApiParam(name = "regionWareVo", value = "查询对象") RegionWareQueryVo regionWareQueryVo) {
        Page<RegionWare> objectPage = new Page<>(page, limit);
        IPage<RegionWare> regionWarePage = regionWareService.selectPage(objectPage, regionWareQueryVo);
        return Result.ok(regionWarePage);
    }

    @ApiOperation("添加开通区域")
    @PostMapping("/save")
    public Result<Void> saveRegionWare(@RequestBody RegionWare regionWare) {
        regionWareService.saveRegionWare(regionWare);
        return Result.ok(null);
    }

    @ApiOperation("删除开通区域")
    @DeleteMapping("/remove/{id}")
    public Result<Void> removeById(@PathVariable Long id) {
        regionWareService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("更新开通区域状态")
    @PostMapping("/updateStatus/{id}/{status}")
    public Result<Void> updateStatus(@PathVariable Long id,
                                     @PathVariable Integer status) {
        regionWareService.updateStatus(id, status);
        return Result.ok(null);
    }
}

