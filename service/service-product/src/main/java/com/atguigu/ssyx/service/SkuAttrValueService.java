package com.atguigu.ssyx.service;


import com.atguigu.ssyx.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * spu属性值 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
public interface SkuAttrValueService extends IService<SkuAttrValue> {

    List<SkuAttrValue> selectBySkuId(Long skuId);

    void removeBySkuId(Long skuId);
}
