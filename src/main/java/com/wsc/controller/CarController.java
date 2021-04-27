package com.wsc.controller;

import com.github.pagehelper.PageInfo;
import com.wsc.VO.CarConditionVO;
import com.wsc.entity.Car;
import com.wsc.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author wsc
 * @date 2021/4/17
 */
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private HttpSession session;

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model){
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser!=null){
            model.addAttribute("loginUser",loginUser);
        }
        //全部二手车基本信息显示
        PageInfo<Car> allCarPage = carService.getByConditionPages(new CarConditionVO(), 1, 5);
        List<Car> list = allCarPage.getList();
        for (Car car:list) {
            System.out.println(car.getId()+"---picture:"+car.getPicture()+"---"+car.getUsedTime()+"年---"+car.getTimeToMarket());
        }
        model.addAttribute("allCar",allCarPage);
        model.addAttribute("title","主页");
        return "car/home";
    }

}
