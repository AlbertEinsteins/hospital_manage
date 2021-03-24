package com.graduate.hospital_manage.response;

public enum  ResultCode {
    SUCCESS(true, 10000, "操作成功!"),


    Fail(false, 10001, "操作失败"),
    UN_AUTHENTICATED(false, 10002, "未登录!"),
    UN_AUTHORISE(false, 10003, "权限不足") ,
    SERVER_ERROR(false, 99999, "系统繁忙，请重试") ;





    boolean success ;
    int code ;
    String message ;

    ResultCode(boolean success, int code, String message) {
        this.success = success ;
        this.code = code ;
        this.message = message ;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
