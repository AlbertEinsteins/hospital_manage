package com.graduate.hospital_manage.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Result
 * @Description TODO
 * @Author: jff
 * @Date: 2020-01-18 15:59
 * @Version: 1.0
 **/
@Data
public class Result implements Serializable {
    private boolean success ;
    private Integer code ;
    private String message ;
    private Object data ;

    private Result() { }

    public Result success(boolean isSuccess) {
        this.success = isSuccess ;
        return this ;
    }
    public Result code(ResultCode resultCode) {
        this.success = resultCode.success ;
        this.code = resultCode.code ;
        this.message = resultCode.message ;
        return this ;
    }
    public Result message(String message) {
        this.message = message ;
        return this ;
    }

    public static Result getInstance(ResultCode code) {
        Result r = new Result() ;
        r.message = code.message ;
        r.code = code.code ;
        r.success = code.success ;
        return r ;
    }

    public static Result SUCCESS() {
        return Result.getInstance(ResultCode.SUCCESS) ;
    }
    public static Result FAILURE() {
        return Result.getInstance(ResultCode.Fail) ;
    }
    public static Result ERROR() {
        return Result.getInstance(ResultCode.SERVER_ERROR) ;
    }

    //失败，自定义消息
    public static Result FAILURE(String msg) {
        Result result = Result.FAILURE() ;
        result.setMessage(msg) ;
        return result ;
    }

    public static Result SUCCESS(Object data) {
        Result result = Result.SUCCESS() ;
        result.setData(data) ;
        return result ;
    }
}
