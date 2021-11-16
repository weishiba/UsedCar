package com.wsc.controller.backControllers;

import org.springframework.stereotype.Controller;

import java.util.Scanner;

/**
 * @author wsc
 * @date 2021/4/29
 */
@Controller
public class indexController {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String src = sc.next();
        //a:2,b:4@a:1,b:2
        String[] split = src.split("@");
        System.out.println(split);

    }

}
