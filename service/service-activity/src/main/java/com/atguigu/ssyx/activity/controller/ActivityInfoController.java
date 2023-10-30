package com.atguigu.ssyx.activity.controller;


import com.atguigu.ssyx.activity.service.ActivityInfoService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.activity.ActivityInfo;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-23
 */

@RestController
@RequestMapping("/admin/activity/activityInfo")
public class ActivityInfoController {

    @Autowired
    private ActivityInfoService activityInfoService;

    @ApiOperation("分页查询")
    @GetMapping("{page}/{limit}")
    public Result<IPage<ActivityInfo>> getPageList(@PathVariable Long page, @PathVariable Long limit) {
        Page<ActivityInfo> infoPage = new Page<>(page, limit);
        IPage<ActivityInfo> activityInfoPage = activityInfoService.getPageList(infoPage);
        return Result.ok(activityInfoPage);
    }

    @ApiOperation("查询")
    @GetMapping("/get/{id}")
    public Result<ActivityInfo> getById(@PathVariable Long id) {
        ActivityInfo byId = activityInfoService.getById(id);
        byId.setActivityTypeString(byId.getActivityType().getComment());
        return Result.ok(byId);
    }

    @ApiOperation("保存")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody ActivityInfo activityInfo) {
        activityInfoService.save(activityInfo);
        return Result.ok(null);
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody ActivityInfo activityInfo) {
        activityInfoService.updateById(activityInfo);
        return Result.ok(null);
    }

    @ApiOperation("删除")
    @DeleteMapping("/remove/{id}")
    public Result<Void> removeById(@PathVariable Long id) {
        activityInfoService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result<Void> removeRows(@RequestBody List<Long> ids) {
        activityInfoService.removeByIds(ids);
        return Result.ok(null);
    }

    @ApiOperation("查询活动规则")
    @GetMapping("findActivityRuleList/{id}")
    public Result<Map<String, Object>> findActivityRuleList(@PathVariable Long id) {
        Map<String, Object> activityRuleMap = activityInfoService.findActivityRuleList(id);
        return Result.ok(activityRuleMap);
    }

    @ApiOperation("添加规则")
    @PostMapping("saveActivityRule")
    public Result<Void> saveActivityRule(@RequestBody ActivityRuleVo activityRuleVo) {
        activityInfoService.saveActivityRule(activityRuleVo);
        return Result.ok(null);
    }

    @ApiOperation("关键字搜索")
    @GetMapping("/findSkuInfoByKeyword/{keyword}")
    public Result<List<SkuInfo>> findSkuInfoByKeyword(@PathVariable String keyword) {
        List<SkuInfo> skuInfoList = activityInfoService.findSkuInfoByKeyword(keyword);
        return Result.ok(skuInfoList);
    }
}

