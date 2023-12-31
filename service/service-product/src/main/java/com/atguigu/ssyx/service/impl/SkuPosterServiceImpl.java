package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.SkuPosterMapper;
import com.atguigu.ssyx.model.product.SkuPoster;
import com.atguigu.ssyx.service.SkuPosterService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
@Service
public class SkuPosterServiceImpl extends ServiceImpl<SkuPosterMapper, SkuPoster> implements SkuPosterService {

    @Override
    public List<SkuPoster> selectBySkuId(Long skuId) {
        LambdaQueryWrapper<SkuPoster> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SkuPoster::getSkuId, skuId);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void removeBySkuId(Long skuId) {
        baseMapper.delete(new LambdaQueryWrapper<SkuPoster>().eq(SkuPoster::getSkuId, skuId));
    }

    @Override
    public void removeBySkuIds(List<Long> skuIds) {
        baseMapper.delete(new LambdaQueryWrapper<SkuPoster>().eq(SkuPoster::getSkuId, skuIds));
    }
}

