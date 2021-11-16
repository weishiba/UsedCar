package com.wsc.service.Impl;

import com.wsc.entity.Permission;
import com.wsc.mapper.PermissionMapper;
import com.wsc.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/5/2
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getAllPermission() {
        return null;
    }

    @Override
    public Integer getByUserId(Long id) {
        return permissionMapper.selectByUserId(id);
    }

    @Override
    public int addPermission(Permission permission) {
        return 0;
    }

    @Override
    public int removePermissionById(Long id) {
        return 0;
    }

    @Override
    public int changePermission(Permission permission) {
        return 0;
    }
}
