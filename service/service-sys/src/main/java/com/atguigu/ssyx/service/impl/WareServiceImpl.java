package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.WareMapper;
import com.atguigu.ssyx.model.sys.Ware;
import com.atguigu.ssyx.service.WareService;
import com.atguigu.ssyx.vo.product.WareQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 仓库表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {

    @Override
    public IPage<Ware> selectWare(Page<Ware> warePage, WareQueryVo wareQueryVo) {
        LambdaQueryWrapper<Ware> queryWrapper = new LambdaQueryWrapper<>();
        return baseMapper.selectPage(warePage, queryWrapper);
    }
}
