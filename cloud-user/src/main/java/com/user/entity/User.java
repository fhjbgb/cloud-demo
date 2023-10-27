package com.user.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String phone;
    private String email;
    private String name;
    private String password;
}
