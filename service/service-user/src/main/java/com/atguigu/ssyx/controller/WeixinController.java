package com.atguigu.ssyx.controller;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.ssyx.common.constant.RedisConst;
import com.atguigu.ssyx.common.exception.SsyxException;
import com.atguigu.ssyx.common.result.Result;
import com.atguigu.ssyx.common.result.ResultCodeEnum;
import com.atguigu.ssyx.common.utils.JwtHelper;
import com.atguigu.ssyx.enums.UserType;
import com.atguigu.ssyx.model.user.Leader;
import com.atguigu.ssyx.model.user.User;
import com.atguigu.ssyx.service.UserService;
import com.atguigu.ssyx.utils.ConstantPropertiesUtil;
import com.atguigu.ssyx.utils.HttpClientUtils;
import com.atguigu.ssyx.vo.user.LeaderAddressVo;
import com.atguigu.ssyx.vo.user.UserLoginVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author qin
 * @date 2023/10/31
 */
@RestController
@RequestMapping("/api/user/weixin")
public class WeixinController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation("微信登录获取openid")
    @GetMapping("/wxLogin/{code}")
    public Result<Map<String, Object>> wxLogin(@PathVariable String code) {
        //1.得到微信返回code临时票据值
        //2.code+appid+appsecret 请求微信接口服务
        String result = obtainWechatTickets(code);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String openid = jsonObject.getString("openid");
        //String sessionKey = jsonObject.getString("session_key");

        User user = userService.getUserOpenId(openid);
        if (Objects.isNull(user)) {
            user = new User();
            user.createNewUser(openid);
            userService.save(user);
        }

        //获取团长信息
        LeaderAddressVo leaderAddressVo = userService.getLeaderAddressByUserId(user.getId());

        //生成token
        String token = JwtHelper.createToken(user.getId(), user.getNickName());

        //获取用户登录信息
        UserLoginVo userLoginVo = userService.getUserLoginVo(user.getId());
        redisTemplate.opsForValue()
                .set(RedisConst.USER_LOGIN_KEY_PREFIX + user.getId(),
                        userLoginVo,
                        RedisConst.USERKEY_TIMEOUT,
                        TimeUnit.DAYS);

        //封装map
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("token", token);
        map.put("leaderAddressVo", leaderAddressVo);
        return Result.ok(map);
    }

    /**
     * 微信返回code临时票据值
     *
     * @param code 临时票据值
     * @return session_key+openid
     */
    private String obtainWechatTickets(String code) {
        String wxOpenAppId = ConstantPropertiesUtil.WX_OPEN_APP_ID;
        String wxOpenAppSecret = ConstantPropertiesUtil.WX_OPEN_APP_SECRET;
        StringBuffer url = new StringBuffer()
                .append("https://api.weixin.qq.com/sns/jscode2session")
                .append("?appid=%s")
                .append("?secret=%s")
                .append("&js_code=%s")
                .append("&grant_type=authorization_code");
        String tokenUrl = String.format(url.toString(), wxOpenAppId, wxOpenAppSecret, code);

        String result;
        try {
            result = HttpClientUtils.get(tokenUrl);
        } catch (Exception e) {
            throw new SsyxException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }
        return result;
    }
}
