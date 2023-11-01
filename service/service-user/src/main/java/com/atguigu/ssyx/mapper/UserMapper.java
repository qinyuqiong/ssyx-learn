package com.atguigu.ssyx.mapper;

import com.atguigu.ssyx.model.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Base64;

/**
 * @author qin
 * @date 2023/10/31
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
