package com.onlineoffice.conf;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description :异常检测
 * ---------------------------------
 * @Author : SG.Y
 * @Date : 2019/5/9 15:24
 */
@RestControllerAdvice
public class MyControllerAdvice {

    /**
     * 全局异常捕捉处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public String errorHandler(Exception e) {
        e.printStackTrace();
        return "服务器内部异常";
    }

}