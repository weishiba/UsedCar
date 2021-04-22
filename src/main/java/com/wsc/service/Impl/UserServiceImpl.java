package com.wsc.service.Impl;

import com.wsc.entity.User;
import com.wsc.mapper.UserMapper;
import com.wsc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public User getUser(long id) {
        return userMapper.selectUser(id);
    }

    @Override
    public User getUser(String name, String password) {
        return userMapper.selectUser1(name,password);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int removeById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int changeUser(User user) {
        return userMapper.updateUser(user);
    }
}
