package com.atguigu.ssyx.service.impl;

import com.atguigu.ssyx.mapper.LeaderMapper;
import com.atguigu.ssyx.mapper.UserDeliveryMapper;
import com.atguigu.ssyx.mapper.UserMapper;
import com.atguigu.ssyx.model.user.Leader;
import com.atguigu.ssyx.model.user.User;
import com.atguigu.ssyx.model.user.UserDelivery;
import com.atguigu.ssyx.service.UserService;
import com.atguigu.ssyx.vo.user.LeaderAddressVo;
import com.atguigu.ssyx.vo.user.UserLoginVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qin
 * @date 2023/10/31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserDeliveryMapper userDeliveryMapper;
    @Autowired
    private LeaderMapper leaderMapper;

    @Override
    public User getUserOpenId(String openid) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getOpenId, openid));
    }

    /**
     * 根据userid获取团长和提货点信息
     *
     * @param id userid
     * @return 团长和提货点信息
     */
    @Override
    public LeaderAddressVo getLeaderAddressByUserId(Long id) {
        UserDelivery userDelivery = userDeliveryMapper.selectOne(new LambdaQueryWrapper<UserDelivery>().eq(UserDelivery::getUserId, id).eq(UserDelivery::getIsDefault, 1));
        if (userDelivery == null) {
            return null;
        }
        Long leaderId = userDelivery.getLeaderId();
        Leader leader = leaderMapper.selectById(leaderId);
        LeaderAddressVo leaderAddressVo = new LeaderAddressVo();
        BeanUtils.copyProperties(leader, leaderAddressVo);
        leaderAddressVo.insertLeaderAddressVo(userDelivery.getUserId(), userDelivery.getLeaderId(), leader.getName(), leader.getPhone(), userDelivery.getWareId(), leader.getStorePath());
        return leaderAddressVo;
    }

    @Override
    public UserLoginVo getUserLoginVo(Long id) {
        User user = baseMapper.selectById(id);
        UserLoginVo userLoginVo = new UserLoginVo();
        BeanUtils.copyProperties(user, userLoginVo);
        userLoginVo.setUserId(id);

        UserDelivery userDelivery = userDeliveryMapper.selectOne(new LambdaQueryWrapper<UserDelivery>().eq(UserDelivery::getUserId, id).eq(UserDelivery::getIsDefault, 1));
        if (userDelivery == null) {
            userLoginVo.setWareId(1L);
            userLoginVo.setLeaderId(1L);
            return userLoginVo;
        }

        userLoginVo.setLeaderId(userLoginVo.getLeaderId());
        userLoginVo.setWareId(userLoginVo.getWareId());
        return userLoginVo;

    }
}
