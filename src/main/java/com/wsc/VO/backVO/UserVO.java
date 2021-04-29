package com.wsc.VO.backVO;

import lombok.Data;

/**
 * @author wsc
 * @date 2021/4/29
 */
@Data
public class UserVO {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String sexEnum;//0：女， 1：男
    private String note;



}
