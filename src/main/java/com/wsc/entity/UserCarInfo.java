package com.wsc.entity;

import lombok.Data;

/**
 * 用户对应的汽车信息
 * @author wsc
 * @date 2021/4/3
 */
@Data
public class UserCarInfo {
    private Long id;
    private Long userId;//用户编号
    private String carId;//车辆编号
    private Integer saleType;//交易类型（0:寄售类型，1:求购类型）
}
