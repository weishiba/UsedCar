package com.wsc.service.impl;

import com.wsc.UsedcarApplication;
import com.wsc.service.CarService;
import com.wsc.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author wsc
 * @date 2021/4/22
 */
@SpringBootTest(classes = UsedcarApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ServiceTest {
    @Autowired
    public UserService userService;
    @Autowired
    public CarService carService;
}
