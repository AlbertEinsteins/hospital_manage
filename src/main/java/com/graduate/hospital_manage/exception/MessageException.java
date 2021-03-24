package com.graduate.hospital_manage.exception;

import com.graduate.hospital_manage.response.ResultCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageException extends Exception {

    public MessageException(ResultCode code, String msg) {
        this.code = code ;
        this.message = msg ;
    }
    public MessageException() { }
    private ResultCode code ;  //状态码

    private String message ;
}
