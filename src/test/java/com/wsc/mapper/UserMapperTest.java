package com.wsc.mapper;

import com.wsc.entity.User;
import org.junit.Test;

/**
 * @author wsc
 * @date 2021/4/5
 */
public class UserMapperTest extends MapperTest{

    @Test
    public void SelectByNameTest(){
        User users = userMapper.selectUserByName("张三");
        System.out.println(users);
    }
}
