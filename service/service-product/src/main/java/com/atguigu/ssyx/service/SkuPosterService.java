package com.atguigu.ssyx.service;


import com.atguigu.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
public interface SkuPosterService extends IService<SkuPoster> {

    List<SkuPoster> selectBySkuId(Long skuId);

    void removeBySkuId(Long skuId);

    void removeBySkuIds(List<Long> skuIds);
}
