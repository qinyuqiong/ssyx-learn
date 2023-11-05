package com.atguigu.ssyx.common.auth;

import com.atguigu.ssyx.vo.user.UserLoginVo;

/**
 * ThreadLocal工具类
 *
 * @author qin
 * @date 2023/11/5
 */
public class AuthContextHolder {

    //用户id
    private static ThreadLocal<Long> userId = new ThreadLocal<>();

    //用户仓库id
    private static ThreadLocal<Long> wareId = new ThreadLocal<>();

    //用户信息对象
    private static ThreadLocal<UserLoginVo> userLoginVo = new ThreadLocal<>();

    public static Long getUserId() {
        return userId.get();
    }

    public static void setUserId(Long userId) {
        AuthContextHolder.userId.set(userId);
    }

    public static Long getWareId() {
        return wareId.get();
    }

    public static void setWareId(Long wareId) {
        AuthContextHolder.wareId.set(wareId);
    }

    public static UserLoginVo getUserLoginVo() {
        return userLoginVo.get();
    }

    public static void setUserLoginVo(UserLoginVo userLoginVo) {
        AuthContextHolder.userLoginVo.set(userLoginVo);
    }
}
