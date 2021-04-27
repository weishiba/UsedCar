package com.wsc.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsc.VO.CarConditionVO;
import com.wsc.entity.Car;
import com.wsc.mapper.CarMapper;
import com.wsc.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/22
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> getAllCar() {
        return carMapper.selectAllCar();
    }

    @Override
    public List<Car> getByCondition(CarConditionVO conditionVO) {
        return carMapper.selectByCondition(conditionVO);
    }

    @Override
    public PageInfo<Car> getByConditionPages(CarConditionVO conditionVO,int pageNow,int pageSize) {
        PageHelper.startPage(pageNow, pageSize);
        List<Car> cars = carMapper.selectByCondition(conditionVO);
        PageInfo<Car> carPageInfo = new PageInfo<>(cars);
        carPageInfo.getList();
        return carPageInfo;
    }

    @Override
    public Car getById(Long id) {
        return carMapper.selectById(id);
    }

    @Override
    public int addCar(Car car) {
        return carMapper.insertCar(car);
    }

    @Override
    public int removeCarById(Long id) {
        return carMapper.deleteCarById(id);
    }

    @Override
    public int changeCar(Car car) {
        return carMapper.updateCar(car);
    }
}
