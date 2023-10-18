package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.SkuImageMapper;
import com.atguigu.ssyx.model.product.SkuImage;
import com.atguigu.ssyx.service.SkuImageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品图片 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage> implements SkuImageService {

    @Override
    public List<SkuImage> selectBySkuId(Long skuId) {
        LambdaQueryWrapper<SkuImage> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuImage::getSkuId, skuId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void removeBySkuId(Long skuId) {
        baseMapper.delete(new LambdaQueryWrapper<SkuImage>().eq(SkuImage::getSkuId, skuId));
    }
}

