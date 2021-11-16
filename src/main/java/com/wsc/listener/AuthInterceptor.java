package com.wsc.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wsc.entity.User;
import com.wsc.service.UserService;
import com.wsc.util.HttpContextUtil;
import com.wsc.util.Result;
import com.wsc.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author wsc
 * @date 2021/5/9
 */

public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = TokenUtil.getRequestToken(request);
        //如果token为空
        if (StringUtils.isBlank(token)) {
            setReturn(response,400,"用户未登录，请先登录");
            return false;
        }
        //1. 根据token，查询用户信息
        User userEntity = userService.findByToken(token);
        //2. 若用户不存在,
        if (userEntity == null) {
            setReturn(response,400,"用户不存在");
            return false;
        }
        //3. 若用户被禁用,
        if (userEntity.getDisabled() == 1) {
            setReturn(response,400,"用户已被禁用");
            return false;
        }
        //4. token失效
        if (userEntity.getExpireTime().isBefore(LocalDateTime.now())) {
            setReturn(response,400,"用户登录凭证已失效，请重新登录");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
    //返回错误信息
    private static void setReturn(HttpServletResponse response, int status, String msg) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        //UTF-8编码
        httpResponse.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        Result build = Result.build(status, msg);
        String json = JSON.toJSONString(build);
        httpResponse.getWriter().print(json);
    }

}
