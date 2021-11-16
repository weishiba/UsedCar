package com.wsc.controller;

import com.github.pagehelper.PageInfo;
import com.wsc.VO.CarConditionVO;
import com.wsc.VO.CarVO;
import com.wsc.entity.Car;
import com.wsc.entity.User;
import com.wsc.service.CarService;
import com.wsc.service.UserService;
import com.wsc.util.JsonResult;
import com.wsc.util.Result;
import com.wsc.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private UserService userService;
    @Autowired
    private HttpSession session;

    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("loginUser", loginUser);
        }
        //全部二手车基本信息显示
        PageInfo<Car> allCarPage = carService.getByConditionPages(new CarConditionVO(), 1, 5);
        List<Car> list = allCarPage.getList();
        for (Car car : list) {
            System.out.println(car.getId() + "---picture:" + car.getPicture() + "---" + car.getUsedTime() + "年---" + car.getTimeToMarket());
        }
        model.addAttribute("allCar", allCarPage);
        model.addAttribute("title", "主页");
        return "car/home";
    }

    @PostMapping("/uploadImg")
    @ResponseBody
    public Map uploadImg(MultipartFile file, HttpServletRequest request) {
        String prefix = "";
        String dateStr = "";
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        try {
            if (file != null) {
                String originalName = file.getOriginalFilename();
                System.out.println("originalName:" + originalName);
                String srcString;
                String filepath;
                prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
                Date date = new Date();
                String uuid = UUID.randomUUID() + "";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                if ("default.jpg".equals(originalName)) {
                    srcString = originalName;
                } else {
                    srcString = dateStr + "\\" + uuid + "." + prefix;

                }
                filepath = "C:\\Users\\18560\\Desktop\\毕设\\前端\\前端layui测试\\usedCar\\images\\" + srcString;


                File files = new File(filepath);
                //打印查看上传路径
                System.out.println("上传图片路径：" + filepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String, Object> map2 = new HashMap<>();
                Map<String, Object> map = new HashMap<>();
                map.put("code", 0);
                map.put("msg", "");
                map.put("data", map2);
                map2.put("src", srcString);
                return map;
            }

        } catch (Exception e) {
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (fileInput != null) {
                    fileInput.close();
                }
            } catch (IOException e) {
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("msg", "");
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result addCar(HttpServletRequest request, CarVO vo) {
        String token = TokenUtil.getRequestToken(request);
        System.out.println("此时的token：" + token);
        User user = userService.findByToken(token);
        if (user != null) {
            System.out.println("token对应的用户：" + user);
            vo.setUserId(user.getId());
        }
        return carService.addCar(vo);
    }

    @GetMapping("/getAbleCar")
    @ResponseBody
    public JsonResult getAbleCar(CarVO vo, Integer page, Integer limit) {
        return carService.getAbleCar(vo, page, limit);
    }

    @GetMapping("/getCarById")
    @ResponseBody
    public Result getCarById(String carId) {
        CarVO car = carService.getById(carId);
        return Result.ok(car);
    }

    @GetMapping("/getDisableCar")
    @ResponseBody
    public JsonResult getDisableCar(CarVO vo, Integer page, Integer limit) {
        return carService.getDisableCar(vo, page, limit);
    }

    @PostMapping("/able")
    @ResponseBody
    public String able(String id) {
        System.out.println("上架车辆");
        return carService.able(id);
    }

    @PostMapping("/disable")
    @ResponseBody
    public String disable(String id) {
        System.out.println("下架车辆");
        return carService.disable(id);
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(CarVO vo) {
        return carService.edit(vo);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        return carService.removeCarById(id);
    }

    @GetMapping("/calPrice")
    @ResponseBody
    public Result calPrice(CarVO vo) {
        if (vo == null) {
            return Result.build(400, "请先输入");
        }
        if (StringUtils.isEmpty(vo.getBrand()) || StringUtils.isEmpty(vo.getType())){
            return Result.build(400,"请填写车辆品牌和类型！");
        }
        float sum = 0;
        if (StringUtils.isNotEmpty(vo.getBrand())) {
            switch (vo.getBrand()) {
                case "8":
                    sum += 8.5;
                    break;
                case "6":
                    sum+= 6.5;
                    break;
                case "3":
                    sum+=3.5;
                    break;
                case "":
                    break;
            }
        }
        if (StringUtils.isNotEmpty(vo.getType())) {
            switch (vo.getType()) {
                case "12":
                    sum += 12.4;
                    break;
                case "8":
                    sum+= 8.6;
                    break;
                case "3":
                    sum+=3.5;
                    break;
                case "":
                    break;
            }
        }
        if (StringUtils.isNotEmpty(vo.getUsedTime())) {
            switch (vo.getUsedTime()) {
                case "3":
                    sum += 3.5;
                    break;
                case "2":
                    sum+= 2.5;
                    break;
                case "1":
                    sum+=1.4;
                    break;
                case "":
                    break;
            }
        }
        if (vo.getMileage() != null) {
            if (vo.getMileage() == 3) {
                sum += 3.5;
            } else if (vo.getMileage() == 2) {
                sum += 2.5;
            } else {
                sum += 1.4;
            }
        }
        System.out.println("估价为："+sum+"万元");
        return Result.ok(sum);
    }

}
