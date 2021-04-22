package com.wsc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 18560
 */
public class PermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("---preHandle---");
        HttpSession session;
        session = request.getSession();
        System.out.println("loginUser:"+session.getAttribute("loginUser"));
        if(session.getAttribute("loginUser")!= null){
            System.out.println("用户已登录，放行");
            return true;
        }
        System.out.println("用户未登录，不放行");
        request.setAttribute("info",2);
        request.getRequestDispatcher("../templates/users/login.html").forward(request,response);
        return false;
    }

}
