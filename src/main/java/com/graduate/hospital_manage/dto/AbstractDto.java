package com.graduate.hospital_manage.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class AbstractDto {
    @NotNull
    private Integer pagenum = 1;
    @NotNull
    private Integer pagesize = 10;
}
