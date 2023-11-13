package com.atguigu.ssyx.home.controller;

import com.atguigu.ssyx.common.auth.AuthContextHolder;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.home.service.HomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author qin
 * @date 2023/11/5
 */
@Api(tags = "首页接口")
@RestController
@RequestMapping("api/home")
public class HomeApiController {
    @Autowired
    private HomeService homeService;

    @ApiOperation("首页数据显示接口")
    @GetMapping("index")
    public Result<Map<String, Object>> index(HttpServletRequest request) {
        Long userId = AuthContextHolder.getUserId();
        Map<String, Object> map = homeService.homeData(userId);
        return Result.ok(map);
    }
}
