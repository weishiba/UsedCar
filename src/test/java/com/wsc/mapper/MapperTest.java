package com.wsc.mapper;

import com.wsc.UsedcarApplication;
import com.wsc.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wsc
 * @date 2021/4/3
 */
@SpringBootTest(classes = UsedcarApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class MapperTest {
    @Resource
    public RoleMapper roleMapper;
    @Resource
    public UserMapper userMapper;
    @Resource
    public AuditMapper auditMapper;
    @Resource
    public CarMapper carMapper;
    @Resource
    public PermissionMapper permissionMapper;
    @Resource
    public PictureMapper pictureMapper;
    @Resource
    public UserCarInfoMapper userCarInfoMapper;

    @Test
    public void RoleMapperTest1(){
        List<Role> roles = roleMapper.selectAllRole();

        System.out.println(roles);
    }
    @Test
    public void RoleMapperTest2(){
        Role role = roleMapper.selectById(5L);

        role.setName("111111");
        int j = roleMapper.updateRole(role);
        System.out.println("修改结果："+j);
    }
    @Test
    public void RoleMapperTest3(){

    }
}
