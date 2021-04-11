package com.wsc.service;

import com.wsc.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/5
 */
@Service
public interface RoleService {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> getAllRole();

    Role getById(Long id);
    /**
     * 添加角色
     * @param role
     * @return
     */
    int addRole(Role role);

    int removeById(Long id);

    int changeRole(Role role);
}
