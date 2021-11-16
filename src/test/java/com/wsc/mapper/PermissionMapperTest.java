package com.wsc.mapper;

import com.wsc.entity.Permission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wsc
 * @date 2021/5/1
 */
class PermissionMapperTest extends MapperTest{

    @Test
    void insertPermission() {
        Permission p = new Permission(2L,3L);
        int i = permissionMapper.insertPermission(p);
        System.out.println("成功："+i);
    }
}