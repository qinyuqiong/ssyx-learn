package com.atguigu.ssyx.activity.service.impl;

import com.atguigu.ssyx.activity.mapper.ActivityInfoMapper;
import com.atguigu.ssyx.activity.mapper.ActivityRuleMapper;
import com.atguigu.ssyx.activity.mapper.ActivitySkuMapper;
import com.atguigu.ssyx.activity.service.ActivityInfoService;
import com.atguigu.ssyx.client.product.ProductFeignClient;
import com.atguigu.ssyx.model.activity.ActivityInfo;
import com.atguigu.ssyx.model.activity.ActivityRule;
import com.atguigu.ssyx.model.activity.ActivitySku;
import com.atguigu.ssyx.model.product.SkuInfo;
import com.atguigu.ssyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 活动表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-10-23
 */
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfo> implements ActivityInfoService {

    @Autowired
    private ActivityRuleMapper activityRuleMapper;

    @Autowired
    private ActivitySkuMapper activitySkuMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public IPage<ActivityInfo> getPageList(Page<ActivityInfo> infoPage) {
        IPage<ActivityInfo> selectPage = baseMapper.selectPage(infoPage, null);
        List<ActivityInfo> records = selectPage.getRecords();
        records.forEach(activityInfo -> activityInfo.setActivityTypeString(activityInfo.getActivityType().getComment()));
        selectPage.setRecords(records);
        return selectPage;
    }

    @Override
    public Map<String, Object> findActivityRuleList(Long id) {
        Map<String, Object> result = new HashMap<>();
        List<ActivityRule> activityRuleList = activityRuleMapper.selectList(new LambdaQueryWrapper<ActivityRule>().eq(ActivityRule::getActivityId, id));
        result.put("activityRuleList", activityRuleList);

        List<ActivitySku> activitySkus = activitySkuMapper.selectList(new LambdaQueryWrapper<ActivitySku>().eq(ActivitySku::getActivityId, id));
        List<Long> skuIds = activitySkus.stream().map(ActivitySku::getSkuId).collect(Collectors.toList());
        List<SkuInfo> skuInfoList = productFeignClient.findSkuInfoList(skuIds);
        result.put("skuInfoList", skuInfoList);
        return result;
    }

    @Override
    public void saveActivityRule(ActivityRuleVo activityRuleVo) {
        Long activityId = activityRuleVo.getActivityId();
        ActivityInfo activityInfo = baseMapper.selectById(activityId);
        activityRuleMapper.delete(new LambdaQueryWrapper<ActivityRule>().eq(ActivityRule::getActivityId, activityId));
        activitySkuMapper.delete(new LambdaQueryWrapper<ActivitySku>().eq(ActivitySku::getActivityId, activityId));

        List<ActivityRule> activityRuleList = activityRuleVo.getActivityRuleList();
        activityRuleList.forEach(activityRule -> {
            activityRule.setActivityId(activityId);
            activityRule.setActivityType(activityInfo.getActivityType());
            activityRuleMapper.insert(activityRule);
        });

        List<ActivitySku> activitySkuList = activityRuleVo.getActivitySkuList();
        activitySkuList.forEach(activitySku -> {
            activitySku.setActivityId(activityId);
            activitySkuMapper.insert(activitySku);
        });
    }

    @Override
    public List<SkuInfo> findSkuInfoByKeyword(String keyword) {
        List<SkuInfo> skuInfoList = productFeignClient.findSkuInfoByKeyword(keyword);
        if (skuInfoList.isEmpty()){
            return skuInfoList;
        }
        List<Long> skuIds = skuInfoList.stream().map(SkuInfo::getId).collect(Collectors.toList());
        List<Long> existSkuIds = baseMapper.selectSkuIdListExist(skuIds);
        List<SkuInfo> result = new ArrayList<>();
        skuInfoList.forEach(skuInfo -> {
            if (!existSkuIds.contains(skuInfo.getId())) {
                result.add(skuInfo);
            }
        });
        return result;
    }
}
