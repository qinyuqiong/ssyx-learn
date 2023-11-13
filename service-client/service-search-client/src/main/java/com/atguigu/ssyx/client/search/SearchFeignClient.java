package com.atguigu.ssyx.client.search;

import com.atguigu.ssyx.model.search.SkuEs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author qin
 * @date 2023/11/13
 */
@FeignClient(value = "service-search")
public interface SearchFeignClient {
    @GetMapping("api/search/sku/inner/findHotSkuList")
    List<SkuEs> findHotSkuList();
}
