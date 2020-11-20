package com.study.studyspringboot.exception;


/**
 * @description: 错误码对照表
 * @author: xiaoyuqin
 * @create: 2020-1-10 14:04
 */
public enum ErrorCode {

    ERR_ERROR(-100, "异常错误"),
    ERR_QUERY_LIMIT(-200, "请求过于频繁"),
    ERR_CLIENT_SIGN_WRONG(-300, "数据签名错误"),
    ERR_PARAM_WRONG(-400, "参数错误"),
    ERR_PARAM_NULL(-500, "缺少参数"),
    ERR_LOGIN_EXPIRY(-600, "登录失效，重新登录"),
    ERR_SYSTEM_BUSY(-8700, "系统繁忙"),
    ERR_RECODE_NOT_EXIST(-9001, "没有该条记录"),
    ERR_OPERATE_RESULT(-9002, "操作失败，请重试"),

    ;

    private int result;
    private String message;

    ErrorCode(int result, String message) {
        this.result = result;
        this.message = message;
    }

    public static ErrorCode getErrError(int code) {
        for (ErrorCode err : values()) {
            if (err.getResult() == code) {
                return err;
            }
        }
        return ERR_ERROR;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
