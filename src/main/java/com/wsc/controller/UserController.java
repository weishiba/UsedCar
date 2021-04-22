package com.wsc.controller;

import com.wsc.VO.RegisterVO;
import com.wsc.entity.User;
import com.wsc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 18560
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("loginUser")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    //跳转主页使用默认跳转代替
    @GetMapping("/index")
    public String login(){
        return "user/login";
    }

    //登录入口
    @PostMapping("/login")
    @ResponseBody
    public String login(Model model,String username, String password) {
            User loginUser = userService.getUser(username, password);
            if (loginUser != null) {
                session.setAttribute("loginUser",loginUser.getUsername());
                return "1";
            }
            return "-1";

    }
    @RequestMapping("/register")
    public String register(){
        return "/user/register";
    }

    @PostMapping("/register2")
    @ResponseBody
    public String register2(Model model, RegisterVO vo){
        User user = userService.getUserByName(vo.getUsername());
        if (user != null){
            //用户名已存在,无法注册
            System.out.println("用户名已存在,无法注册");
            return "0";
        }else if (!vo.getPassword().equals(vo.getConfirmpassword())){
            //两次密码输入不一致
            System.out.println("两次密码输入不一致");
            return "-1";

        }else{
            user = new User(vo.getUsername(),vo.getPassword(),vo.getSex(),vo.getNote());
            userService.addUser(user);
            System.out.println("注册成功");
            return "1";
        }

    }
    @RequestMapping("/leave")
    public String leave(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "user/login";
    }

    private Cookie getCookie(Cookie[] cookies, String name) {
        Cookie cookie = null;
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (name.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie;
    }

    @GetMapping("/getUsers")
    public ModelAndView getUsers(HttpServletRequest request,Model model){
        List<User> allUser = userService.getAllUser();
        model.addAttribute("allUser",allUser);
        model.addAttribute("title","所有用户");
        return new ModelAndView("user/user","user",model);
    }


    @RequestMapping("/getuser/{id}")
    @ResponseBody
    public String getuser(@PathVariable long id){
        System.out.println("111111111111");
        return userService.getUser(id).toString();
    }

    @RequestMapping("/get")
    @ResponseBody
    public User getuser(){
        System.out.println("111111111111");
        User user = new User();
        user.setUsername("张三");
        user.setPassword("11111");
        System.out.println("11111");
        return user;
    }


}
