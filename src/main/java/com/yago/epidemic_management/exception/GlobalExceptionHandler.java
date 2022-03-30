package com.yago.epidemic_management.exception;

import com.yago.epidemic_management.common.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Yago
 * @Date: 2022/3/23 18:56
 * Description:  处理统一异常的handler。可以拦截异常，返回统一的MyException对象
 * @ControllerAdvice(assignableTypes = {ExceptionController.class})
 * 在类上加上@ControllerAdvice注解这个类就成为了全局异常处理类，
 * 当然你也可以通过 assignableTypes指定特定的 Controller 类，
 * 让异常处理类只处理特定类抛出的异常。
 * 这种方式不常用所以改用@ExceptionHandler(Exception.class)。
 **/
@ControllerAdvice  //代表这个类是全局异常处理类：处理所有的异常
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * //拦截未知所有异常,一般情况下一个方法特定处理一种异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        log.error("Default Exception:", e);
        return ResultResponse.error(ExceptionEnum.SYSTEM_ERROR);
    }

    /**
     * 拦截自定义业务异常,一般情况下一个方法特定处理一种异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public ResultResponse handleMyException(MyException e) {
        log.error("MyException:", e);
        return ResultResponse.error(e.getCode(), e.getMessage());
    }

    /**
     * 拦截检验信息异常,一般情况下一个方法特定处理一种异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultResponse handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        return handleBindingResult(e.getBindingResult());
    }

    private ResultResponse handleBindingResult(BindingResult result) {
        //把异常处理为对外暴露的提示
        List<String> list = new ArrayList<>();
        //是不是包含错误
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError objectError : allErrors) {
                String message = objectError.getDefaultMessage();
                list.add(message);
            }
        }
        if (list == null) {
            return ResultResponse.error(ExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return ResultResponse.error(ExceptionEnum.REQUEST_PARAM_ERROR.getCode(), list.toString());
    }
}
