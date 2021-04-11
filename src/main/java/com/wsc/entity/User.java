package com.wsc.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer sex;//0：女， 1：男
    private String note;
    private Integer disabled;//是否禁用（0：否，1：是）

}
