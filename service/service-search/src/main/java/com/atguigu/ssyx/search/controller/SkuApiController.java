package com.atguigu.ssyx.search.controller;

import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.search.SkuEs;
import com.atguigu.ssyx.search.service.SkuApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author user
 * @date 2023/10/19
 */
@RestController
@RequestMapping("api/search/sku")
public class SkuApiController {
    @Autowired
    private SkuApiService skuApiService;

    @GetMapping("inner/upperSku/{skuId}")
    public Result<Void> upperSku(@PathVariable Long skuId) {
        skuApiService.upperSku(skuId);
        return Result.ok(null);
    }

    @GetMapping("inner/lowerSku/{skuId}")
    public Result<Void> lowerSku(@PathVariable Long skuId) {
        skuApiService.lowerSku(skuId);
        return Result.ok(null);
    }

    @ApiOperation(value = "获取爆品商品")
    @GetMapping("inner/findHotSkuList")
    public List<SkuEs> findHotSkuList() {
        return skuApiService.findHotSkuList();
    }
}
