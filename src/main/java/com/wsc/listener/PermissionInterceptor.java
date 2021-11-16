package com.wsc.listener;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 18560
 */
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("---preHandle---");
        System.out.println("loginUser:"+request.getSession().getAttribute("loginUser"));
        if(request.getSession().getAttribute("loginUser")!= null){
            System.out.println("用户已登录，放行");
            return true;
        }
        System.out.println("用户未登录，不放行");
        response.sendRedirect(request.getContextPath()+"/user/index");
        return false;
    }

}
