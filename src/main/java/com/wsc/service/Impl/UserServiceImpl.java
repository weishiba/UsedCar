package com.wsc.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wsc.VO.backVO.UserVO;
import com.wsc.entity.User;
import com.wsc.enums.SexEnum;
import com.wsc.mapper.UserMapper;
import com.wsc.service.UserService;
import com.wsc.util.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public JsonResult getUsers(Integer page, Integer limit) {
        JsonResult jsonResult = new JsonResult();
        IPage<User> userIPage = new Page<>(page,limit);
        IPage<User> result = userMapper.selectPage(userIPage, null);
        List<User> allUser = result.getRecords();
        System.out.println("显示条数："+allUser.size());
        List<UserVO> list = new ArrayList<>();
        for (User user:allUser) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user,userVO);
            userVO.setSexEnum(SexEnum.getDesc(user.getSex()));
            list.add(userVO);
        }
        jsonResult.setData(list);
        jsonResult.setCode(0);
        jsonResult.setCount(userMapper.selectCount(null));
        jsonResult.setMessage("");
        return jsonResult;
    }
}
