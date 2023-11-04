package com.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public User getUserbyPhone(String phone);
}
