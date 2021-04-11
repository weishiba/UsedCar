package com.wsc.controller;

import com.wsc.entity.User;
import com.wsc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 18560
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return userService.getAllUser();
    }

    @RequestMapping("/getuser/{id}")
    public String getuser(@PathVariable long id){
        System.out.println("111111111111");
        return userService.getUser(id).toString();
    }

    @RequestMapping("/get")
    public User getuser(){
        System.out.println("111111111111");
        User user = new User();
        user.setUsername("张三");
        user.setPassword("11111");
        System.out.println("11111");
        return user;
    }
}
