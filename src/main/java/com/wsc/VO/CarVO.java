package com.wsc.VO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author wsc
 * @date 2021/5/5
 */
@Data
public class CarVO {
    //车辆id
    private String carId;
    //缩略图
    private String picture;
    //图片id
    private String pictureId;
    //品牌
    private String brand;
    //型号
    private String model;
    //颜色
    private String color;
    //汽车类型
    private String type;
    //座位数
    private Integer seatNum;
    //上市时间
    private String timeToMarket;
    //新车价
    private BigDecimal newCarPrice;
    //标价
    private BigDecimal price;
    //标价（查询条件）
    private BigDecimal priceStr;
    private BigDecimal priceEnd;
    //最低车龄
    private String usedTime;
    //最少行驶里程数(公里)
    private Double mileage;
    //上架情况（0.未上架/1.已上架/2.下架）
    private Integer sale;
    //上架时间
    private String saleTime;
    //备注
    private String note;
    //交易类型（0:寄售类型，1:求购类型）
    private Integer saleType;


    private String picture1Path;
    private String picture2Path;
    private String picture3Path;
    private String picture4Path;
    private String picture5Path;
    private String picture6Path;
    private String picture7Path;

    //上传者id
    private Long userId;
    private String phone;
}
