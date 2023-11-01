package com.atguigu.ssyx.service;

import com.atguigu.ssyx.model.user.User;
import com.atguigu.ssyx.vo.user.LeaderAddressVo;
import com.atguigu.ssyx.vo.user.UserLoginVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author qin
 * @date 2023/10/31
 */
public interface UserService extends IService<User> {
    User getUserOpenId(String openid);

    LeaderAddressVo getLeaderAddressByUserId(Long id);

    UserLoginVo getUserLoginVo(Long id);
}
