package com.wsc.service;

import com.wsc.VO.RegisterVO;
import com.wsc.VO.backVO.UserVO;
import com.wsc.entity.User;
import com.wsc.util.JsonResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 18560
 */
@Service
public interface UserService {
    List<User> getAllUser();

    JsonResult getUser(long id);

    User getUser(String name, String password);

    User getUserByName(String name);

    String addUser(RegisterVO vo);

    String edit(RegisterVO vo);

    int removeById(Long id);

    int changeUser(User user);

    //查询未被禁用的用户
    JsonResult getAbleUsers(UserVO vo, Integer page, Integer limit);
    //查询被禁用的用户
    JsonResult getDisableUsers(UserVO vo, Integer page, Integer limit);

    String delete(Long id);

    //禁用用户
    public String disable(Long id);
    //解封用户
    public String able(Long id);

    /**
     * 为user生成token
     * @param user
     * @return
     */
    String createToken(User user);
    /**
     * 根据token去修改用户token ，使原本token失效
     * @param token
     */
    void logout(String token);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    User findByToken(String token);
}
