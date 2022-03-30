package com.yago.epidemic_management.interceptor;

import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.utils.JWTUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: YaGo
 * @Date: 2022/3/29 2:14
 * Description:
 **/
public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //http的header中获得token
//        String token = request.getHeader("USER_LOGIN_TOKEN");
        String token = request.getHeader("token");
        System.out.println(token);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //token不存在
        if (token == null || token.equals("")) throw new MyException(ExceptionEnum.NEED_LOGIN);

        //验证token
        String sub = JWTUtils.validateToken(token);
        if (sub == null || sub.equals(""))
            throw new MyException(ExceptionEnum.BAD_TOKEN);

        //更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)) {
            String newToken = JWTUtils.createToken(sub);
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN, newToken);
        }
        return true;
    }
}
