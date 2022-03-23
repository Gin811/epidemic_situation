package com.yago.epidemic_management.exception;

/**
 * @Author: Yago
 * @Date: 2022/3/22 16:17
 * Description:
 **/
public class MyException extends RuntimeException {
    private final Integer code;
    private final String message;

    public MyException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public MyException(ExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(), exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
