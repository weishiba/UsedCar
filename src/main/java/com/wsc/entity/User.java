package com.wsc.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private Integer sex;//0：女， 1：男
    private String note;
    private Integer disabled;//是否禁用（0：否，1：是）
    /**
     * token 登陆凭证
     */
    private String token;
    /**
     * token 过期时间
     */
    private LocalDateTime expireTime;
    /**
     *  登录时间
     */
    private LocalDateTime loginTime;

    public User(){}

    public User(String username,String password,String phone,Integer sex,String note,Integer disabled){
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.note = note;
        this.disabled = disabled;
    }

}
