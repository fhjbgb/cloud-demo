package com.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserTokenMapper extends BaseMapper<UserToken> {

    public void deleteById(@Param("userId") Long userid);


}
