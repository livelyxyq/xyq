package com.study.studyspringboot.vo;

import com.study.studyspringboot.exception.ErrorCode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: xiaoyuqin
 * @create: 2020-1-10 14:04
 */
@Data
@NoArgsConstructor
public class ResponseResult<T> {

    private int code = 0;

    private String message = "SUCCESS";

    private T data;

    public ResponseResult(T data) {
        this.code = 0;
        this.message = "SUCCESS";
        this.data = data;
    }

    public ResponseResult(T data, String message) {
        this.message = message;
        this.data = data;
    }

    public ResponseResult(String message) {
        this.message = message;
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseResult error(int code, String message) {
        return new ResponseResult(code, message);
    }

    public static ResponseResult error(ErrorCode err) {
        return new ResponseResult(err.getResult(), err.getMessage());
    }

    public static ResponseResult success() {
        return new ResponseResult();
    }

    public static ResponseResult success(String message) {
        return new ResponseResult(message);
    }

    public ResponseResult success(T data) {
        return new ResponseResult(data);
    }

    public ResponseResult success(T data, String message) {
        return new ResponseResult(data, message);
    }

    public ResponseResult success(T data, String message, Integer code) {
        return new ResponseResult(code, message, data);
    }
}
