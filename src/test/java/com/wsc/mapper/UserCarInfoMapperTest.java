package com.wsc.mapper;

import com.wsc.entity.UserCarInfo;
import org.junit.jupiter.api.Test;

/**
 * @author wsc
 * @date 2021/5/6
 */
class UserCarInfoMapperTest extends MapperTest{

    @Test
    void selectAllUserCarInfo() {
    }

    @Test
    void selectById() {
    }

    @Test
    void selectByUserId() {
    }

    @Test
    void selectByCarId() {
        UserCarInfo userCarInfo = userCarInfoMapper.selectByCarId("1");
        System.out.println(userCarInfo);
    }

    @Test
    void insertUserCarInfo() {
    }

    @Test
    void deleteUserCarInfoById() {
    }

    @Test
    void updateUserCarInfo() {
    }
}