package com.yago.epidemic_management.exception;

/**
 * @Author: Yago
 * @Date: 2022/3/22 14:41
 * Description:
 **/
public enum ExceptionEnum {

    //业务异常  10000
    NEED_USER_NAME(10001, "用户名不能为空"),
    NEED_PASSWORD(10002, "密码不能为空"),
    PASSWORD_TOO_SHORT(10003, "密码长度不能小于8位"),
    NAME_EXISTED(10004, "不允许重名"),
    INSERT_FAILED(10005, "插入失败"),
    WRONG_PASSWORD(10006, "密码错误"),
    NEED_LOGIN(10007, "需要登录"),
    UPDATE_FAILED(10008, "更新失败"),
    NEED_ADMIN(10009, "需要管理员权限"),

    //系统异常  20000
    SYSTEM_ERROR(20000, "系统异常");

    Integer code;
    String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
