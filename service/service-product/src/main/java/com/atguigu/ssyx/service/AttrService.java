package com.atguigu.ssyx.service;


import com.atguigu.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
public interface AttrService extends IService<Attr> {

    List<Attr> findByAttrGroupId(Long attrGroupId);
}
