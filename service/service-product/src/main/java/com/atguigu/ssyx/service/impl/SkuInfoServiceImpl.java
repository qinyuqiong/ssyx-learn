package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.SkuInfoMapper;
import com.atguigu.ssyx.model.product.SkuAttrValue;
import com.atguigu.ssyx.model.product.SkuImage;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.model.product.SkuPoster;
import com.atguigu.ssyx.service.SkuAttrValueService;
import com.atguigu.ssyx.service.SkuImageService;
import com.atguigu.ssyx.service.SkuInfoService;
import com.atguigu.ssyx.service.SkuPosterService;
import com.atguigu.ssyx.vo.product.SkuInfoQueryVo;
import com.atguigu.ssyx.vo.product.SkuInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Autowired
    private SkuImageService skuImageService;
    @Autowired
    private SkuPosterService skuPosterService;
    @Autowired
    private SkuAttrValueService skuAttrValueService;

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

    @Override
    public void saveSkuInfo(SkuInfoVo skuInfoVo) {
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo, skuInfo);
        baseMapper.insert(skuInfo);

        saveSkuInfo(skuInfoVo, skuInfo);

    }

    @Override
    public SkuInfoVo getSkuInfoById(Long id) {
        SkuInfo skuInfo = baseMapper.selectById(id);
        List<SkuImage> skuImages = skuImageService.selectBySkuId(id);
        List<SkuPoster> skuPosters = skuPosterService.selectBySkuId(id);
        List<SkuAttrValue> skuAttrValues = skuAttrValueService.selectBySkuId(id);

        SkuInfoVo skuInfoVo = new SkuInfoVo();
        BeanUtils.copyProperties(skuInfo, skuInfoVo);
        skuInfoVo.setSkuImagesList(skuImages);
        skuInfoVo.setSkuPosterList(skuPosters);
        skuInfoVo.setSkuAttrValueList(skuAttrValues);

        return skuInfoVo;
    }

    @Override
    public void updateSkuInfoById(SkuInfoVo skuInfoVo) {
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(skuInfoVo, skuInfo);
        baseMapper.updateById(skuInfo);

        skuImageService.removeBySkuId(skuInfo.getId());
        skuPosterService.removeBySkuId(skuInfo.getId());
        skuAttrValueService.removeBySkuId(skuInfo.getId());

        saveSkuInfo(skuInfoVo, skuInfo);
    }

    @Override
    public void removeSkuInfoById(Long id) {
        skuImageService.removeBySkuId(id);
        skuPosterService.removeBySkuId(id);
        skuAttrValueService.removeBySkuId(id);
        baseMapper.selectById(id);
    }

    @Override
    public void removeSkuInfoByIds(List<Long> ids) {
        skuImageService.removeBySkuIds(ids);
        skuPosterService.removeBySkuIds(ids);
        skuAttrValueService.removeBySkuIds(ids);
        baseMapper.deleteBatchIds(ids);
    }

    @Override
    public void updateNewPerson(Long id, Integer status) {
        SkuInfo skuInfo = baseMapper.selectById(id);
        skuInfo.setIsNewPerson(status);
        baseMapper.updateById(skuInfo);
    }

    @Override
    public void updateCheck(Long id, Integer status) {
        SkuInfo skuInfo = baseMapper.selectById(id);
        skuInfo.setCheckStatus(status);
        baseMapper.updateById(skuInfo);
    }

    @Override
    public void updatePublish(Long id, Integer status) {
        SkuInfo skuInfo = baseMapper.selectById(id);
        skuInfo.setPublishStatus(status);
        baseMapper.updateById(skuInfo);
    }

    private void saveSkuInfo(SkuInfoVo skuInfoVo, SkuInfo skuInfo) {
        List<SkuPoster> skuPosterList = skuInfoVo.getSkuPosterList();
        skuPosterList.forEach(skuPoster -> skuPoster.setSkuId(skuInfo.getId()));
        skuPosterService.saveBatch(skuPosterList);

        List<SkuImage> skuImagesList = skuInfoVo.getSkuImagesList();
        skuImagesList.forEach(skuImage -> skuImage.setSkuId(skuInfo.getId()));
        skuImageService.saveBatch(skuImagesList);

        List<SkuAttrValue> skuAttrValueList = skuInfoVo.getSkuAttrValueList();
        skuAttrValueList.forEach(skuAttrValue -> skuAttrValue.setSkuId(skuInfo.getId()));
        skuAttrValueService.saveBatch(skuAttrValueList);
    }
}
