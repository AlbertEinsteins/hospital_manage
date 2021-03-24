package com.graduate.hospital_manage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StatisticDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate ;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate ;


    private Integer tid ;
}
