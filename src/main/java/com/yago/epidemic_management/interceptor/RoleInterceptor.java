package com.yago.epidemic_management.interceptor;

import com.yago.epidemic_management.exception.ExceptionEnum;
import com.yago.epidemic_management.exception.MyException;
import com.yago.epidemic_management.service.UserService;
import com.yago.epidemic_management.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.PrintWriter;

/**
 * @Author: YaGo
 * @Date: 2022/3/29 2:14
 * Description:     管理员拦截器
 **/
public class RoleInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.http的header中获得token
        String token = request.getHeader("token");
//        System.out.println("登录拦截器：" + token);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //token不存在
        if (token == null || token.equals("")) throw new MyException(ExceptionEnum.NEED_LOGIN);

        //2.验证token
        // 返回的是用户信息
        String sub = JWTUtils.validateToken(token);
//        System.out.println("validateToken返回的信息："+sub);
        if (sub == null || sub.equals(""))
            throw new MyException(ExceptionEnum.BAD_TOKEN);

        //3.更新token有效时间 (如果需要更新其实就是产生一个新的token)
        if (JWTUtils.isNeedUpdate(token)) {
            String newToken = JWTUtils.createToken(sub);
            response.setHeader(JWTUtils.USER_LOGIN_TOKEN, newToken);
        }

        //4.验证是不是管理员
        int userid = Integer.parseInt(sub);
        boolean adminRole = userService.checkAdminRoleById(userid);
        if (!adminRole) {
//            Map<String,Object> noRole=new HashMap<>();
//            noRole.put("msg","not Role");
//            noRole.put("status",10009);
//            noRole.put("data",null);
            PrintWriter writer = new HttpServletResponseWrapper(response).getWriter();
            writer.write("{\n"
                    + "    \"status\": 10009,\n"
                    + "    \"msg\": \"not Role\",\n"
                    + "    \"data\": null\n"
                    + "}");
            return false;
        }

//      System.out.println("拦截器userID为："+userid);
        return true;
//        //解决因为多了空格导致乱码报错
//        String[] tokenString = token.split(" ");
//        int userId = Integer.parseInt(String.valueOf(JWT.decode(tokenString[1]).getSubject()));
//        System.out.println("解析的信息为："+userId);
    }
}
