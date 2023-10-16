package com.atguigu.ssyx.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.acl.RolePermission;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.service.RegionWareService;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/ssyx/region-ware")
public class RegionWareController {
    @Autowired
    private RegionWareService regionWareService;

    @ApiOperation("分页查询")
    @GetMapping("/{page}/{limit}")
    public Result index(@PathVariable Long page, @PathVariable Long limit, RegionWareQueryVo regionWareQueryVo) {
        Page<RegionWare> objectPage = new Page<>(page, limit);
        IPage<RegionWare> regionWarePage = regionWareService.selectPage(objectPage, regionWareQueryVo);
        return Result.ok(regionWarePage);
    }
}

