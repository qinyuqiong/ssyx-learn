package com.atguigu.ssyx.common.result;

import lombok.Data;

/**
 * @author 17273
 * @date 2023/7/13
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    /**
     * 构造私有化
     */
    private Result() {
    }

    /**
     * 建造者
     *
     * @param data    数据
     * @param code    代码
     * @param message 消息
     * @param <T>     跑函数
     * @return 返回值
     */
    public static <T> Result<T> build(T data, Integer code, String message) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

    /**
     * 创建
     *
     * @param data           数据
     * @param resultCodeEnum 结果码池
     * @param <T>            抛函数
     * @return Result
     */
    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(resultCodeEnum.getCode());
        result.setMsg(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 成功
     *
     * @param <T> 抛函数
     * @return Result
     */
    public static <T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }


    /**
     * 失败
     *
     * @param <T> 抛函数
     * @return Result
     */
    public static <T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }
}
