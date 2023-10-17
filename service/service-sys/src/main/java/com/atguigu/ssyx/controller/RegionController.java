package com.atguigu.ssyx.controller;


import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.sys.Region;
import com.atguigu.ssyx.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Api(tags = "地区管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/sys/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @ApiOperation("根据关键字查询信息")
    @GetMapping("/findRegionByKeyword/{keyword}")
    public Result<List<Region>> findRegionByKeyword(@PathVariable String keyword) {
        List<Region> list = regionService.selectByName(keyword);
        return Result.ok(list);
    }
}

