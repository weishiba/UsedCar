package com.wsc.service;

import com.wsc.entity.UserCarInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Service
public interface UserCarInfoService {

    /**
     * 查询
     * @return
     */
    List<UserCarInfo> getAllUserCarInfo();

    UserCarInfo getById(Long id);

    List<UserCarInfo> getByUserId(Long uid);

    /**
     * 添加
     * @param userCarInfo
     * @return
     */
    int addUserCarInfo(UserCarInfo userCarInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    int removeUserCarInfoById(Long id);

    /**
     * 修改
     * @param userCarInfo
     * @return
     */
    int changeUserCarInfo(UserCarInfo userCarInfo);
}
