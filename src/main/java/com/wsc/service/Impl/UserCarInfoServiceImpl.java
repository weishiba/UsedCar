package com.wsc.service.Impl;

import com.wsc.entity.UserCarInfo;
import com.wsc.mapper.UserCarInfoMapper;
import com.wsc.service.UserCarInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Service
public class UserCarInfoServiceImpl implements UserCarInfoService {
    @Autowired
    private UserCarInfoMapper userCarInfoMapper;

    @Override
    public List<UserCarInfo> getAllUserCarInfo() {
        return userCarInfoMapper.selectAllUserCarInfo();
    }

    @Override
    public UserCarInfo getById(Long id) {
        return userCarInfoMapper.selectById(id);
    }

    @Override
    public List<UserCarInfo> getByUserId(Long uid) {
        return userCarInfoMapper.selectByUserId(uid);
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
