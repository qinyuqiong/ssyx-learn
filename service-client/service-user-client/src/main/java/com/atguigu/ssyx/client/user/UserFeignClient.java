package com.atguigu.ssyx.client.user;

import com.atguigu.ssyx.vo.user.LeaderAddressVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author qin
 * @date 2023/11/13
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {

    @GetMapping("/api/user/leader/inner/getUserAddressByUserId/{userId}")
    LeaderAddressVo getUserAddressByUserId(@PathVariable Long userId);
}
