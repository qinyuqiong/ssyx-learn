package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.RoleMapper;
import com.atguigu.ssyx.model.acl.AdminRole;
import com.atguigu.ssyx.model.acl.Role;
import com.atguigu.ssyx.service.AdminRoleService;
import com.atguigu.ssyx.service.RoleService;
import com.atguigu.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 17273
 * @date 2023/7/14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;

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

    @Override
    public Map<String, Object> findRoleByAdminId(Long adminId) {
        List<Role> allRolesList = baseMapper.selectList(null);

        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminRole::getAdminId, adminId);
        List<AdminRole> adminRoles = adminRoleService.list(queryWrapper);
        List<Long> roleIdList = adminRoles.stream().map(AdminRole::getRoleId).collect(Collectors.toList());

        List<Role> assignRoleList = new ArrayList<>();
        for (Role role : allRolesList) {
            if (roleIdList.contains(role.getId())) {
                assignRoleList.add(role);
            }
        }

        Map<String, Object> result = new HashMap<>(2);
        result.put("allRolesList", allRolesList);
        result.put("assignRoles", assignRoleList);
        return result;
    }

    @Override
    public void saveAdminRole(Long adminId, List<Long> roleId) {
        //删除用户已经保存的角色
        LambdaQueryWrapper<AdminRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminRole::getAdminId, adminId);
        adminRoleService.remove(queryWrapper);
        //重新分配
        List<AdminRole> adminRoles = new ArrayList<>();
        for (Long roleId1 : roleId) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(adminId);
            adminRole.setRoleId(roleId1);
            adminRoles.add(adminRole);
        }
        adminRoleService.saveBatch(adminRoles);
    }
}
