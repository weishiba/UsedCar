package com.wsc.mapper;

import com.wsc.entity.UserCarInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Mapper
public interface UserCarInfoMapper {

    /**
     * 查询
     * @return
     */
    List<UserCarInfo> selectAllUserCarInfo();

    UserCarInfo selectById(Long id);

    List<UserCarInfo> selectByUserId(Long uid);

    /**
     * 添加
     * @param userCarInfo
     * @return
     */
    int insertUserCarInfo(UserCarInfo userCarInfo);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteUserCarInfoById(Long id);

    /**
     * 修改
     * @param userCarInfo
     * @return
     */
    int updateUserCarInfo(UserCarInfo userCarInfo);
}
