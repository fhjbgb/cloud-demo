package com.user.service;

import com.user.dao.UserMapper;
import com.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void addUser(User user) {
        userMapper.insert(user);
    }
}
