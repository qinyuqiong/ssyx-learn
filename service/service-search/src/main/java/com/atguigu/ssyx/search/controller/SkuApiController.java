package com.atguigu.ssyx.search.controller;

import com.atguigu.ssyx.search.service.SkuApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 * @date 2023/10/19
 */
@RestController
@RequestMapping("api/search/sku")
public class SkuApiController {
    @Autowired
    private SkuApiService skuApiService;
}
