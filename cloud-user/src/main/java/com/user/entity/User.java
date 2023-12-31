package com.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    private String phone;
    private String email;
    private String name;
    private String password;
    private Long snowid;
}
