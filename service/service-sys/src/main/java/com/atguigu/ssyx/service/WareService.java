package com.atguigu.ssyx.service;

import com.atguigu.ssyx.model.sys.Ware;
import com.atguigu.ssyx.vo.product.WareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 仓库表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
public interface WareService extends IService<Ware> {

    IPage<Ware> selectWare(Page<Ware> warePage, WareQueryVo wareQueryVo);
}
