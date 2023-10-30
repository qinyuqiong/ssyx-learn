package com.atguigu.ssyx.controller;

import com.atguigu.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 17273
 * @date 2023/7/14
 */

@RestController
@RequestMapping("admin/acl/index")
public class IndexController {

    @PostMapping("/login")
    public Result<Map<String, String>> login() {
        Map<String, String> map = new HashMap<>();
        map.put("token", "admin-token");
        return Result.ok(map);
    }

    @GetMapping("/info")
    public Result<Map<String, String>> info() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "atguigu");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    @PostMapping("/logout")
    public Result<Void> logout() {
        return Result.ok(null);
    }
}
