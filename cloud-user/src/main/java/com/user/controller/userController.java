package com.user.controller;

import com.example.clouddemo.vo.R;
import com.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.service.UserService;

@RestController
public class userController {
    @Autowired
    private UserService userService;

    @PostMapping("user")
    public R addUser(User user){
        userService.addUser(user);
        return R.success();
    }
}
