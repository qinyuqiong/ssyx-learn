package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.WareMapper;
import com.atguigu.ssyx.model.sys.Ware;
import com.atguigu.ssyx.service.WareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author user
 * @date 2023/10/16
 */
@Service
public class WareServiceImpl extends ServiceImpl<WareMapper, Ware> implements WareService {
}