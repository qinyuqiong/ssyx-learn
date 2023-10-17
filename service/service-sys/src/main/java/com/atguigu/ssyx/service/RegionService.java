package com.atguigu.ssyx.service;

import com.atguigu.ssyx.model.sys.Region;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-16
 */
public interface RegionService extends IService<Region> {

    List<Region> selectByName(String keyword);
}
