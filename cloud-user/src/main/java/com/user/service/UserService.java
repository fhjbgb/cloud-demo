package com.user.service;

import com.common.exception.ConditionException;
import com.user.Util.TokenUtil;
import com.user.dao.UserMapper;
import com.user.dao.UserTokenMapper;
import com.user.entity.User;
import com.user.Util.SnowFlake;
import com.user.entity.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysql.cj.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;


    public void addUser(User user) {
        userMapper.insert(user);
    }

    public void addUserBySonw(User user) {
        SnowFlake snowFlake = new SnowFlake(0,0);
        Long snowid = snowFlake.nextId();
        user.setSnowid(snowid);
        userMapper.insert(user);
    }

    public String userLoginToken(User user) throws Exception {
        //验证信息，返回refreshtonken
        String phone = user.getPhone();
        String password = user.getPassword();
        if(StringUtils.isNullOrEmpty(phone) || StringUtils.isNullOrEmpty(password)){
            throw new ConditionException("信息错误");
        }
        //获取db中的user信息
        User dbuser = userMapper.getUserbyPhone(phone);
        //比较密码
        if(password != dbuser.getPassword()){
            throw new ConditionException("密码错误");
        }
        //生成token并返回
        return TokenUtil.generateToken(dbuser.getId());
    }

    public Map<String, Object> userLoginDtokens(User user) throws Exception {
        //同样的验证用户信息
        //验证信息，返回refreshtonken
        String phone = user.getPhone();
        String password = user.getPassword();
        if(StringUtils.isNullOrEmpty(phone) || StringUtils.isNullOrEmpty(password)){
            throw new ConditionException("信息错误");
        }
        //获取db中的user信息
        User dbuser = userMapper.getUserbyPhone(phone);
        //比较密码
        if(password != dbuser.getPassword()){
            throw new ConditionException("密码错误");
        }
        //将两种token放到map种返回给前端。
        Map<String,Object> res = new HashMap<>();
        String accessToken = TokenUtil.generateToken(dbuser.getId());
        String refreshToken = TokenUtil.generateRefreshToken(dbuser.getId());
        //refreshToken会保存到数据库中，由于用户登录操作生成了新的refreshtoken，所以要更新，先删除旧的再加入新的
        userTokenMapper.deleteById(dbuser.getId());
        UserToken userToken = new UserToken(dbuser.getId(),refreshToken);
        userTokenMapper.insert(userToken);
        res.put("accessToken",accessToken);
        res.put("refreshToken",refreshToken);
        return  res;
    }


}
