package com.wsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * @author 18560
 */
@SpringBootApplication
@EnableTransactionManagement
public class UsedcarApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsedcarApplication.class, args);
    }

}
