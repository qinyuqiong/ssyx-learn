package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.RoleMapper;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.service.RoleService;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.StringUtil;
import org.springframework.stereotype.Service;

/**
 * @author 17273
 * @date 2023/7/14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    /**
     * 分页查询
     *
     * @param rolePage    分页条件
     * @param roleQueryVo 查询条件
     * @return 结果
     */
    @Override
    public IPage<Role> selectPage(Page<Role> rolePage, RoleQueryVo roleQueryVo) {
        String roleName = roleQueryVo.getRoleName();
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtil.isNotEmpty(roleName)) {
            queryWrapper.like(Role::getRoleName, roleName);
        }
        return baseMapper.selectPage(rolePage, queryWrapper);
    }
}
