package com.wsc.service.Impl;

import com.wsc.entity.Car;
import com.wsc.entity.UserCarInfo;
import com.wsc.mapper.CarMapper;
import com.wsc.mapper.UserCarInfoMapper;
import com.wsc.mapper.UserMapper;
import com.wsc.service.UserCarInfoService;
import com.wsc.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Service
public class UserCarInfoServiceImpl implements UserCarInfoService {
    @Autowired
    private UserCarInfoMapper userCarInfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CarMapper carMapper;


    @Override
    public List<UserCarInfo> getAllUserCarInfo() {
        return userCarInfoMapper.selectAllUserCarInfo();
    }

    @Override
    public UserCarInfo getById(Long id) {
        return userCarInfoMapper.selectById(id);
    }


    @Override
    public JsonResult getSellByUserId(Long userId) {
        List<UserCarInfo> userCarInfos = userCarInfoMapper.selectByUserId(userId);
        List<Car> list = new ArrayList<>();
        for (UserCarInfo info:userCarInfos){
            if (info.getSaleType() == 0){
                Car car = carMapper.selectById(info.getCarId());
                if (car.getSale() == 1){
                    list.add(car);
                }

            }
        }
        System.out.println("寄售车辆集合"+list);
        System.out.println("寄售车辆数量"+list.size());
        return JsonResult.success(list,list.size());
    }

    @Override
    public JsonResult getBuyByUserId(Long userId) {
        List<UserCarInfo> userCarInfos = userCarInfoMapper.selectByUserId(userId);
        List<Car> list = new ArrayList<>();
        for (UserCarInfo info:userCarInfos){
            if (info.getSaleType() == 1){
                Car car = carMapper.selectById(info.getCarId());
                if (car.getSale() == 1){
                    list.add(car);
                }
            }
        }
        System.out.println("求购车辆集合"+list);
        System.out.println("求购车辆数量"+list.size());
        return JsonResult.success(list,list.size());
    }

    @Override
    public UserCarInfo getByCarId(String carId) {
        return userCarInfoMapper.selectByCarId(carId);
    }

    @Override
    public int addUserCarInfo(UserCarInfo userCarInfo) {
        return userCarInfoMapper.insertUserCarInfo(userCarInfo);
    }

    @Override
    public int removeUserCarInfoById(Long id) {
        return userCarInfoMapper.deleteUserCarInfoById(id);
    }

    @Override
    public int changeUserCarInfo(UserCarInfo userCarInfo) {
        return userCarInfoMapper.updateUserCarInfo(userCarInfo);
    }
}
