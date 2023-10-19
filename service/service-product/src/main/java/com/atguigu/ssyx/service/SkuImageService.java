package com.atguigu.ssyx.service;


import com.atguigu.ssyx.model.product.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品图片 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
public interface SkuImageService extends IService<SkuImage> {

    List<SkuImage> selectBySkuId(Long id);

    void removeBySkuId(Long skuId);

    void removeBySkuIds(List<Long> skuIds);
}
