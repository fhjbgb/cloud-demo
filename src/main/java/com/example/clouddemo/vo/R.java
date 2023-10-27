package com.example.clouddemo.vo;

import lombok.Data;

@Data
public class R<T>{
    private int code;
    private String msg;
    private T data;

    public R(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public R(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static R success(){
        return new R(200,"success");
    }
}
