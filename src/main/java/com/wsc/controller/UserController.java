package com.wsc.controller;

import com.wsc.VO.RegisterVO;
import com.wsc.entity.User;
import com.wsc.service.UserService;
import com.wsc.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        return "user/index";
    }

    //登录入口
    @PostMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request,Model model,String username, String password) {
            User loginUser = userService.getUser(username, password);
            if (loginUser != null) {
                session.setAttribute("loginUser",loginUser);
                model.addAttribute("loginUser",loginUser);
                System.out.println("登陆成功");
                System.out.println("loginUser:"+request.getSession().getAttribute("loginUser"));
                return "1";
            }
            return "-1";

    }


    @PostMapping("/register")
    @ResponseBody
    public String register(Model model, RegisterVO vo){
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
            user = new User(vo.getUsername(),vo.getPassword(),vo.getPhone(),vo.getSex(),vo.getNote(),0);
            userService.addUser(user);
            System.out.println("注册成功");
            return "1";
        }

    }
    @RequestMapping("/leave")
    public String leave(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "user/index";
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
    @ResponseBody
    public JsonResult getUsers(HttpServletRequest request,Model model,Integer page,Integer limit){

        return userService.getUsers(page,limit);
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
