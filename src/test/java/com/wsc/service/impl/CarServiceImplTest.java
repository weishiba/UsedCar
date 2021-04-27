package com.wsc.service.impl;

import com.github.pagehelper.PageInfo;
import com.wsc.VO.CarConditionVO;
import com.wsc.entity.Car;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author wsc
 * @date 2021/4/22
 */
public class CarServiceImplTest extends ServiceTest {
    @Test
    public void Test1(){
        CarConditionVO vo = new CarConditionVO();
        vo.setBrand("宝马");
        vo.setPriceStr(BigDecimal.valueOf(10));
        vo.setPriceEnd(BigDecimal.valueOf(50));
        PageInfo<Car> carPageInfo = carService.getByConditionPages(vo, 1, 2);
        System.out.println(carPageInfo);
    }
}
