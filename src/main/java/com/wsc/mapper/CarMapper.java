package com.wsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsc.VO.CarConditionVO;
import com.wsc.entity.Car;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/4
 */
@Mapper
public interface CarMapper extends BaseMapper<Car> {

    /**
     * 查询
     * @return
     */
    List<Car> selectAllCar();

    List<Car> selectByCondition(CarConditionVO conditionVO);

    Car selectById(Long id);

    /**
     * 添加
     * @param car
     * @return
     */
    int insertCar(Car car);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteCarById(Long id);

    /**
     * 修改
     * @param car
     * @return
     */
    int updateCar(Car car);
}
