package com.common.vo;

import lombok.Data;

@Data
public class Response<T>{
    private int code;
    private String msg;
    private T data;

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(T data){
        this.code = 200;
        this.msg="成功";
        this.data = data;
    }



    public static Response success(){
        return new Response(200,"success");
    }


}
