package com.yueyou.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用返回类
 * @param <T>
 * @author 喜洋洋
 */
@Data
public class BaseResponse<T> implements Serializable {
//    需要返回的信息
    private String msg;
//    需要的返回的 对象
    private T Data;
//    需要返回的状态码等等
    private Integer code;

    public BaseResponse(T data) {
        Data = data;
    }

    public BaseResponse(String msg, T data) {
        this.msg = msg;
        Data = data;
    }

    public BaseResponse(String msg) {
        this.msg = msg;
    }

    public BaseResponse(String msg, T data, Integer code) {
        this.msg = msg;
        Data = data;
        this.code = code;
    }
}
