package com.wsc.service;

import com.github.pagehelper.PageInfo;
import com.wsc.VO.CarConditionVO;
import com.wsc.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Service
public interface CarService {

    /**
     * 查询
     * @return
     */
    List<Car> getAllCar();

    List<Car> getByCondition(CarConditionVO conditionVO);

    PageInfo<Car> getByConditionPages(CarConditionVO conditionVO,int pageNow,int pageSize);

    Car getById(Long id);

    /**
     * 添加
     * @param car
     * @return
     */
    int addCar(Car car);

    /**
     * 删除
     * @param id
     * @return
     */
    int removeCarById(Long id);

    /**
     * 修改
     * @param car
     * @return
     */
    int changeCar(Car car);
}
