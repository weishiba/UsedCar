package com.wsc.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 18560
 */

@Data
public class Car {
    //id
    private Long id;
    //图片id
    private Long pictureId;
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
    //上市时间
    private String timeToMarket;
    //新车价
    private BigDecimal newCarPrice;
    //标价
    private BigDecimal price;
    //车龄
    private String usedTime;
    //行驶里程数(公里)
    private Double mileage;
    //上架情况（0.未上架/1.已上架/2.下架）
    private Integer sale;
    //上架时间
    private String saleTime;
    //备注
    private String note;
    //交易类型（0:寄售类型，1:求购类型）
    private Integer saleType;

}
