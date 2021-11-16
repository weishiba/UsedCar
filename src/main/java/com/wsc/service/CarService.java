package com.wsc.service;

import com.github.pagehelper.PageInfo;
import com.wsc.VO.CarConditionVO;
import com.wsc.VO.CarVO;
import com.wsc.entity.Car;
import com.wsc.util.JsonResult;
import com.wsc.util.Result;
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

    //根据Id查看车辆详情
    JsonResult getCar(String id);

    //查看上架中的车辆
    JsonResult getAbleCar(CarVO vo, Integer page, Integer limit);

    //查看未上架中的车辆
    JsonResult getDisableCar(CarVO vo, Integer page, Integer limit);

    CarVO getById(String id);

    /**
     * 添加
     * @param VO
     * @return
     */
    Result addCar(CarVO VO);

    /**
     * 删除
     * @param id
     * @return
     */
    String removeCarById(String id);

    //下架
     String disable(String id);
    //上架
     String able(String id);

    /**
     * 修改
     * @param car
     * @return
     */
    int changeCar(Car car);
    String edit(CarVO vo);

    PageInfo<Car> getByConditionPages(CarConditionVO conditionVO, Integer page, Integer limit);
}
