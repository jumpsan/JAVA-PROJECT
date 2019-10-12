package com.example.bbs.interceptor;

import com.example.bbs.utils.JjwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理员登陆拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String adminToken=request.getHeader("adminToken");
        System.out.println("LoginInterceptor...");
        if(token==null && adminToken==null){
            return false;
        }else{
            //解析
            Claims claims=null;
            if(token!=null){
                 claims= JjwtUtils.parseJWT(token);
            }
            Claims adminClaims=null;
            if(adminToken!=null){
                adminClaims=JjwtUtils.parseJWT(adminToken);
            }

            return claims!=null || adminClaims!=null;
        }

    }
}
