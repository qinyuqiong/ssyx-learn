package com.atguigu.ssyx.service;

import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 17273
 * @date 2023/7/14
 */
public interface RoleService extends IService<Role> {
    /**
     * 分页查询
     *
     * @param rolePage    分页
     * @param roleQueryVo 查询框框
     * @return 角色
     */
    IPage<Role> selectPage(Page<Role> rolePage, RoleQueryVo roleQueryVo);
}
