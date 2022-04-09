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
 * @Date: 2022/4/9 0:03
 * Description:     登录拦截器
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.http的header中获得token
        String token = request.getHeader("token");
//        System.out.println("Login登录拦截器：" + token);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //token不存在
        if (token == null || token.equals("")) throw new MyException(ExceptionEnum.NEED_LOGIN);

        //2.验证token
        // 返回的是用户信息
        String sub = JWTUtils.validateToken(token);
        if (sub == null || sub.equals(""))
            throw new MyException(ExceptionEnum.BAD_TOKEN);


        //3.更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)) {
            String newToken = JWTUtils.createToken(sub);
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN, newToken);
        }
        return true;
//        //解决因为多了空格导致乱码报错
//        String[] tokenString = token.split(" ");
//        int userId = Integer.parseInt(String.valueOf(JWT.decode(tokenString[1]).getSubject()));
//        System.out.println("解析的信息为："+userId);
    }
}
