package com.atguigu.ssyx.common.result;

import lombok.Getter;

/**
 * @author 17273
 * @Description 返回结果状态码残类
 * @ClassName ResultCodeEnum
 * @date 2023/7/13
 */
@Getter
public enum ResultCodeEnum {


    SERVICE_ERROR(2012, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    ILLEGAL_REQUEST(205, "非法请求"),
    REPEAT_SUBMIT(206, "重复提交"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),

    ORDER_PRICE_ERROR(210, "订单商品价格变化"),
    ORDER_STOCK_FALL(204, "订单库存锁定失败"),
    CREATE_ORDER_FAIL(210, "创建订单失败"),

    COUPON_GET(220, "优惠券已经领取"),
    COUPON_LIMIT_GET(221, "优惠券已发放完毕"),

    URL_ENCODE_ERROR(216, "URL编码失败"),
    ILLEGAL_CALLBACK_REQUEST_ERROR(217, "非法回调请求"),
    FETCH_ACCESSTOKEN_FAILD(218, "获取accessToken失败"),
    FETCH_USERINFO_ERROR(219, "获取用户信息失败"),


    SKU_LIMIT_ERROR(230, "购买个数不能大于限购个数"),
    REGION_OPEN(240, "该区域已开通"),
    REGION_NO_OPEN(240, "该区域未开通"),
    PAYMENT_WAITING(242, "订单支付中"),
    PAYMENT_SUCCESS(241, "订单支付成功"),
    PAYMENT_FAIL(243, "订单支付失败"),
    STATUS_ERROR(244, "状态异常"),
    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 失败
     */
    FAIL(201, "失败");


    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String message;

    /**
     * 操作
     */
    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
