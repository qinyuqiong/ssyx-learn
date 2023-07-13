package com.atguigu.ssyx.common.exception;

import com.atguigu.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公共处理失败的原因
 * @author 17273
 * @date 2023/7/13
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理至new ApiException处理失败的原因
     *
     * @param e 处理失败的原因
     * @return 统一返回结果
     */
    //异常处理
    @ExceptionHandler(Exception.class)
    //返回json
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail(null);
    }

    /**
     * 处理自定义一场
     * @param e 处理失败的原因
     * @return 统一返回结果
     */
    @ExceptionHandler(SsyxException.class)
    //返回json
    @ResponseBody
    public Result error(SsyxException e) {
        e.printStackTrace();
        return Result.fail(e.getMessage());
    }
}
