package com.atguigu.ssyx.utils;

import com.atguigu.ssyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 17273
 * @date 2023/7/21
 */
public class PermissionHelper {

    public static List<Permission> buildPermission(List<Permission> allList) {
        List<Permission> trees = new ArrayList<>();
        for (Permission permission : allList) {
            if (permission.getPid() == 0) {
                permission.setLevel(1);
                trees.add(findChildren(permission, allList));
            }

        }
        return trees;
    }

    private static Permission findChildren(Permission permission, List<Permission> allList) {
        permission.setChildren(new ArrayList<>());
        for (Permission child : allList) {
            if (child.getPid().equals(permission.getId())) {
                child.setLevel(permission.getLevel() + 1);
                permission.getChildren().add(findChildren(child, allList));
            }
        }
        return permission;
    }
}
