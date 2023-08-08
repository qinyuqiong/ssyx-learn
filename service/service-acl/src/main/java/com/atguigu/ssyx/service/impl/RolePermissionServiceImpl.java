package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.RolePermissionMapper;
import com.atguigu.ssyx.model.acl.RolePermission;
import com.atguigu.ssyx.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 17273
 * @date 2023/7/30
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
