package com.wsc.VO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author wsc
 * @date 2021/4/22
 */
@Data
public class CarConditionVO implements Serializable {
    private static final long serialVersionUID = -6159384122625028249L;
    //品牌
    private String brand;
    //型号
    private String model;
    //颜色
    private String color;
    //汽车类型
    private Integer type;
    //座位数
    private Integer seatNum;
    //标价
    private BigDecimal priceStr;
    private BigDecimal priceEnd;
    //车龄
    private String usedTime;
    //行驶里程数(公里)
    private Double mileage;
    //上架情况（0.未上架/1.已上架/2.下架）
    private Integer sale;
    //上架时间
    private String saleTimeStr;
    private String saleTimeEnd;
    //备注
    private String note;
    //交易类型（0:寄售类型，1:求购类型）
    private Integer saleType;
}
