package com.wsc.controller;

import com.wsc.service.UserCarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author wsc
 * @date 2021/4/17
 */
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private UserCarInfoService carService;
    @Autowired
    private HttpSession session;

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model){
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser!=null){
            model.addAttribute("username",loginUser);
        }

        model.addAttribute("title","主页");
        return "car/home";
    }

}
