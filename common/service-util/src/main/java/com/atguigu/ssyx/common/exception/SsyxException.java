package com.atguigu.ssyx.common.exception;

import com.atguigu.ssyx.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常
 * @author 17273
 * @date 2023/7/13
 */
@Data
public class SsyxException extends RuntimeException {
    private Integer code;


    /**
     * @param code    错误码
     * @param message 错误信息
     */
    public SsyxException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * @param resultCodeEnum 错误码
     */
    public SsyxException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
