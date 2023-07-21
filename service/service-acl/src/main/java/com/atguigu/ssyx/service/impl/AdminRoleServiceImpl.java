package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.AdminRoleMapper;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.service.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 17273
 * @date 2023/7/18
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
}
