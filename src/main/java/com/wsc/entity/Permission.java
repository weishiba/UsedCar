package com.wsc.entity;

import lombok.Data;

/**
 * 用户权限实体
 * @author 18560
 */
@Data
public class Permission {
    private Long id;
    private Long userId;
    private Long roleId;

}
