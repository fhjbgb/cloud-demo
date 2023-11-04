package com.user.controller;

import com.common.vo.Response;
import com.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.user.service.UserService;

import java.util.Map;

@RestController
public class userController {
    @Autowired
    private UserService userService;

    @PostMapping("user")
    public Response addUser(@RequestBody User user){
        userService.addUser(user);
        return Response.success();
    }

    @PostMapping("/userSnow")
    public Response addUserbySnow(@RequestBody User user){
        userService.addUserBySonw(user);
        return Response.success();
    }
    //单token登录
    @PostMapping("user-tokens")
    public Response userLoginToken(@RequestBody User user) throws Exception {
        String token = userService.userLoginToken(user);
        return new Response(token);
    }
    //双tonken登录
    @PostMapping("/user-dtk")
    public Response userLoginDtokens(@RequestBody User user) throws Exception {
        Map<String,Object> res = userService.userLoginDtokens(user);
        return new Response(res);
    }
    //session登录,先摸了
}
