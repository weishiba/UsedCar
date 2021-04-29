package com.wsc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wsc.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wsc
 * @date 2021/4/3
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> selectAllRole();

    Role selectById(Long id);
    /**
     * 添加角色
     * @param role
     * @return
     */
    int insertRole(Role role);

    int deleteById(Long id);

    int updateRole(Role role);


}
