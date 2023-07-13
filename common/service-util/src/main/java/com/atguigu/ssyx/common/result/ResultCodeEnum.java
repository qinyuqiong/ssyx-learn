package com.atguigu.ssyx.common.result;

import lombok.Getter;

/**
 * @Description 返回结果状态码残类
 * @ClassName ResultCodeEnum
 * @author 17273
 * @date 2023/7/13
 */
@Getter
public enum ResultCodeEnum {

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
    private String message;

    /**
     * 操作
     */
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
