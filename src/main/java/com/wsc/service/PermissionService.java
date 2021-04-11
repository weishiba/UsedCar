package com.wsc.service;

import com.wsc.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Service
public interface PermissionService {
    /**
     * 查询
     * @return
     */
    List<Permission> getAllPermission();

    Permission getById(Long id);

    /**
     * 添加
     * @param permission
     * @return
     */
    int addPermission(Permission permission);

    /**
     * 删除
     * @param id
     * @return
     */
    int removePermissionById(Long id);

    /**
     * 修改
     * @param permission
     * @return
     */
    int changePermission(Permission permission);
}
