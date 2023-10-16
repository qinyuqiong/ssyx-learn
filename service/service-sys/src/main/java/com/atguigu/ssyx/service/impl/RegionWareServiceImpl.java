package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.RegionWareMapper;
import com.atguigu.ssyx.model.sys.RegionWare;
import com.atguigu.ssyx.service.RegionWareService;
import com.atguigu.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public Page<RegionWare> selectPage(Page<RegionWare> objectPage, RegionWareQueryVo regionWareQueryVo) {
        String keyWord = regionWareQueryVo.getKeyword();
        if (keyWord.isEmpty()) {
            Page<RegionWare> regionWarePage = baseMapper.selectPage(objectPage, null);
            return regionWarePage;
        }
        LambdaQueryWrapper<RegionWare> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(RegionWare::getRegionName, keyWord).or().like(RegionWare::getWareName, keyWord);
        Page<RegionWare> regionWarePage = baseMapper.selectPage(objectPage, queryWrapper);
        return regionWarePage;
    }
}
