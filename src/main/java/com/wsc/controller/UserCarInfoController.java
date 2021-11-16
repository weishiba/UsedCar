package com.wsc.controller;

import com.wsc.service.UserCarInfoService;
import com.wsc.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wsc
 * @date 2021/5/12
 */
@RestController
@RequestMapping("/ucInfo")
public class UserCarInfoController {
    @Autowired
    private UserCarInfoService infoService;

    @GetMapping("/getSellByUserId")
    public JsonResult getSellByUserId(Long userId){
        return infoService.getSellByUserId(userId);
    }

    @GetMapping("/getBuyByUserId")
    public JsonResult getBuyByUserId(Long userId){
        return infoService.getBuyByUserId(userId);
    }
}
