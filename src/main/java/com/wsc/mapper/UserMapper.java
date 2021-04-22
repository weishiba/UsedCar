package com.wsc.mapper;

import com.wsc.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author 18560
 */
@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUser();

    /**
     * 根据用户id查询用户
    * @param id
     * @return
     */
    User selectUser(long id);

    User selectUser1(String username, String password);

    User selectUserByName(String name);

    int insertUser(User user);

    int deleteById(Long id);

    int updateUser(User user);

}
