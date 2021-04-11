package com.wsc.service;

import com.wsc.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 18560
 */
@Service
public interface UserService {
    List<User> getAllUser();

    User getUser(long id);

    List<User> getUserByName(String name);

    int addUser(User user);

    int removeById(Long id);

    int changeUser(User user);
}
