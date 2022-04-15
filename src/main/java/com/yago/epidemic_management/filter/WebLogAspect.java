package com.yago.epidemic_management.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: Yago
 * @Date: 2022/3/23 14:18
 * Description:  控制台打印请求和响应的信息
 **/
@Aspect     //定义一个切面
@Component       //让spring识别到这个类
public class WebLogAspect {
    private final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    //1.指定拦截点
    @Pointcut("execution(public * com.yago.epidemic_management.controller.*.*(..))")
    public void webLog() {

    }

    @Before("webLog()")    //2.在调用webLog()之前要做的事情
    public void doBefore(JoinPoint joinPoint) {
        //3.收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();   //得到请求

        //4.真正记录内容
        log.info("URl:" + request.getRequestURI());
        log.info("HTTP_METHOD:" + request.getMethod());
        log.info("IP:" + request.getRemoteAddr());
        log.info("CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getDeclaringTypeName());
        log.info("ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "res", pointcut = "webLog()")
    public void doAfterReturning(Object res) throws JsonProcessingException {
        //5.处理完请求，返回内容
        log.info("RESPONSE:" + new ObjectMapper().writeValueAsString(res));
    }
}
