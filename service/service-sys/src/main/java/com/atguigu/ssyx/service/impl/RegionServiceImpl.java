package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.RegionMapper;
import com.atguigu.ssyx.model.sys.Region;
import com.atguigu.ssyx.service.RegionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Override
    public List<Region> selectByName(String keyword) {
        LambdaQueryWrapper<Region> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Region::getName, keyword);
        List<Region> list = baseMapper.selectList(queryWrapper);
        return list;
    }
}
