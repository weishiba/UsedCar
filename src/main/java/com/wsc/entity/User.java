package com.wsc.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private Integer sex;//0：女， 1：男
    private String note;
    private Integer disabled;//是否禁用（0：否，1：是）

    public User(){}

    //disabled是否默认为0？如果是就不用加入构造方法
    public User(String username,String password,String phone,Integer sex,String note,Integer disabled){
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.note = note;
        this.disabled = disabled;
    }

}
