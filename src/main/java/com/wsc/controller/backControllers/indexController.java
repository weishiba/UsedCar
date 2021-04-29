package com.wsc.controller.backControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author wsc
 * @date 2021/4/29
 */
@Controller
public class indexController {

    @GetMapping("index")
    public String index(){
        return "index";
    }
}
