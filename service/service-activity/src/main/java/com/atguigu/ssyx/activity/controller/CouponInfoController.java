package com.atguigu.ssyx.activity.controller;


import com.atguigu.ssyx.activity.service.CouponInfoService;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.model.activity.CouponInfo;
import com.atguigu.ssyx.vo.activity.CouponRuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2023-10-23
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/activity/couponInfo")
public class CouponInfoController {

    @Autowired
    private CouponInfoService couponInfoService;


    @ApiOperation("分页查询")
    @GetMapping("{page}/{limit}")
    public Result<IPage<CouponInfo>> getPageList(@PathVariable Long page, @PathVariable Long limit) {
        Page<CouponInfo> infoPage = new Page<>(page, limit);
        IPage<CouponInfo> result = couponInfoService.getPageList(infoPage);
        return Result.ok(result);
    }

    @ApiModelProperty("添加优惠券信息")
    @PostMapping("/save")
    public Result<Void> save(@RequestBody CouponInfo couponInfo) {
        couponInfoService.save(couponInfo);
        return Result.ok(null);
    }

    @ApiOperation("根据id")
    @GetMapping("get/{id}")
    public Result<CouponInfo> getById(@PathVariable Long id) {
        return Result.ok(couponInfoService.getInfoById(id));
    }

    @ApiOperation("根据id更新")
    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody CouponInfo couponInfo) {
        couponInfoService.updateById(couponInfo);
        return Result.ok(null);
    }

    @ApiOperation("删除")
    @DeleteMapping("/remove/{id}")
    public Result<Void> removeById(@PathVariable Long id) {
        couponInfoService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result<Void> removeRows(@RequestBody List<Long> idList) {
        couponInfoService.removeByIds(idList);
        return Result.ok(null);
    }

    @ApiOperation("根据id查规则")
    @GetMapping("/findCouponRuleList/{id}")
    public Result<Map<String, Object>> findCouponRuleList(@PathVariable Long id){
        return Result.ok(couponInfoService.findCouponRuleList(id));
    }
}

