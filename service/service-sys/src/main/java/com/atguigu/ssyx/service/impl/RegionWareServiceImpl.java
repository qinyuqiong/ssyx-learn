package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.common.exception.SsyxException;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.mapper.RegionWareMapper;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.service.RegionWareService;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Service
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {

    @Override
    public IPage<RegionWare> selectPage(Page<RegionWare> objectPage, RegionWareQueryVo regionWareQueryVo) {
        String keyWord = regionWareQueryVo.getKeyword();
        LambdaQueryWrapper<RegionWare> queryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyWord)) {
            queryWrapper.like(RegionWare::getRegionName, keyWord).or().like(RegionWare::getWareName, keyWord);
        }
        IPage<RegionWare> regionWarePage = baseMapper.selectPage(objectPage, queryWrapper);
        return regionWarePage;
    }

    @Override
    public void saveRegionWare(RegionWare regionWare) {
        //查询是否已经存在
        LambdaQueryWrapper<RegionWare> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RegionWare::getRegionId, regionWare.getRegionId());
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new SsyxException(ResultCodeEnum.REGION_OPEN);
        }
        //若不存在则添加
        baseMapper.insert(regionWare);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        if (status != 1 && status != 0) {
            throw new SsyxException(ResultCodeEnum.STATUS_ERROR);
        }
        RegionWare regionWare = baseMapper.selectById(id);
        regionWare.setStatus(status);
        baseMapper.updateById(regionWare);
    }
}
