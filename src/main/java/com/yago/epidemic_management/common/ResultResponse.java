package com.yago.epidemic_management.common;

import com.yago.epidemic_management.exception.ExceptionEnum;

/**
 * @Author: Yago
 * @Date: 2022/3/22 14:31
 * Description:
 **/
public class ResultResponse<T> {
    public static final int OK_CODE = 10000;
    private static final String OK_MSG = "SUCCESS";
    private Integer status;
    private String msg;
    private T data;

    public ResultResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ResultResponse() {
        this(OK_CODE, OK_MSG);
    }

    public static <T> ResultResponse<T> success() {
        return new ResultResponse<>();
    }

    public static <T> ResultResponse<T> success(T result) {
        ResultResponse<T> response = new ResultResponse<>();
        response.setData(result);
        return response;
    }

    public static <T> ResultResponse<T> error(Integer code, String msg) {
        return new ResultResponse<>(code, msg);
    }

    public static <T> ResultResponse<T> error(ExceptionEnum ex) {
        return new ResultResponse<>(ex.getCode(), ex.getMsg());
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
