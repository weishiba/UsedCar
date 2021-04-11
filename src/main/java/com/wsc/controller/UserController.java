package com.wsc.controller;

import com.wsc.entity.User;
import com.wsc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 18560
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public ModelAndView getUsers(HttpServletRequest request,Model model){
        List<User> allUser = userService.getAllUser();
        model.addAttribute("allUser",allUser);
        model.addAttribute("title","所有用户");
        return new ModelAndView("users/user","user",model);
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

    @RequestMapping("/empty")
    public String empty() {
        return "users/empty";
    }
}
