package com.wsc.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wsc
 * @date 2021/4/17
 */
@Data
public class RegisterVO implements Serializable {
    private static final long serialVersionUID = -4022135847186982347L;

    private String username;
    private String password;
    private String phone;
    private String confirmpassword;
    private Integer sex;//0：女， 1：男
    private String note;
}
