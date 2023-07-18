package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.AdminMapper;
import com.atguigu.ssyx.model.acl.Admin;
import com.atguigu.ssyx.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 17273
 * @date 2023/7/16
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}
