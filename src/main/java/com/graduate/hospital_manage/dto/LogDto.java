package com.graduate.hospital_manage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.graduate.hospital_manage.model.Log;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LogDto extends AbstractDto {
    private String level ;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDateTime from ;
}
