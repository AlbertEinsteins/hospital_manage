package com.graduate.hospital_manage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDtoImpl<T> extends AbstractDto {
    private T data ;

    public BaseDtoImpl() {
    }

    public BaseDtoImpl(T data) {
        this.data = data ;
    }
}
