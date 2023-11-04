package com.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserToken {
    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    private Long userid;
    private String rtoken;

    public UserToken(Long userid, String rtoken) {
        this.userid = userid;
        this.rtoken = rtoken;
    }
}
