package com.example.bbs.utils;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

public class Authorization {
    public static boolean verify(HttpServletRequest request,Integer id){
        boolean result=false;
        try {
            String token=request.getHeader("token");
            Integer userId=null;
            Claims claims=null;
            if(token!=null){
                claims = JjwtUtils.parseJWT(token);
                userId = (Integer)claims.get("userId");
            }
            String adminToken=request.getHeader("adminToken");
            Claims adminClaims=null;
            if(adminToken!=null){
                adminClaims=JjwtUtils.parseJWT(adminToken);
                userId=(Integer)adminClaims.get("userId");
            }
            if(userId!=null && userId == id){
                result=true;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

}
