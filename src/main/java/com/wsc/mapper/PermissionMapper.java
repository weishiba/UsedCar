package com.wsc.mapper;

import com.wsc.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/4
 */
@Mapper
public interface PermissionMapper {

    /**
     * 查询
     * @return
     */
    List<Permission> selectAllPermission();

    Permission selectById(Long id);

    /**
     * 添加
     * @param permission
     * @return
     */
    int insertPermission(Permission permission);

    /**
     * 删除
     * @param id
     * @return
     */
    int deletePermissionById(Long id);

    /**
     * 修改
     * @param permission
     * @return
     */
    int updatePermission(Permission permission);
}
