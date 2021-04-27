package com.wsc.mapper;

import com.wsc.entity.Car;
import org.junit.Test;

/**
 * @author wsc
 * @date 2021/4/22
 */

public class CarMapperTest extends MapperTest{

    @Test
    public void test1(){

    }

    @Test
    public void test2(){
        Car car = carMapper.selectById((long) 1);
        car.setColor("黑色");
        car.setBrand("宝马");
        car.setModel("X5");
        car.setNote("测试");
        int i = carMapper.updateCar(car);
        System.out.println("更新结果："+i);
    }
}
