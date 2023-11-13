package com.atguigu.ssyx.search.service;

import com.atguigu.ssyx.model.search.SkuEs;

import java.util.List;

/**
 * @author user
 * @date 2023/10/19
 */
public interface SkuApiService {
    void upperSku(Long skuId);

    void lowerSku(Long skuId);

    List<SkuEs> findHotSkuList();
}
