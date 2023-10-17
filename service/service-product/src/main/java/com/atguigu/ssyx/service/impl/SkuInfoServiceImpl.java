package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.SkuInfoMapper;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.service.SkuInfoService;
import com.atguigu.ssyx.vo.product.SkuInfoQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * sku信息 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-17
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {

    @Override
    public IPage<SkuInfo> selectPage(Page<SkuInfo> pageParam, SkuInfoQueryVo skuInfoQueryVo) {
        String skuType = skuInfoQueryVo.getSkuType();
        String keyword = skuInfoQueryVo.getKeyword();
        Long categoryId = skuInfoQueryVo.getCategoryId();

        LambdaQueryWrapper<SkuInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(skuType), SkuInfo::getSkuType, skuType);
        queryWrapper.like(!StringUtils.isEmpty(keyword), SkuInfo::getSkuName, keyword);
        queryWrapper.eq(!StringUtils.isEmpty(categoryId), SkuInfo::getCategoryId, categoryId);
        return baseMapper.selectPage(pageParam, queryWrapper);
    }
}
