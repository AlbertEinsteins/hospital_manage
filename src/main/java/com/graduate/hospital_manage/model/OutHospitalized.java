package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;


@TableName("unhospitalized")
@Getter
@Setter
@Accessors(chain = true)
public class OutHospitalized implements Serializable {
    @TableId
    private String uid ;
    private String name ;
    @TableField("sex")
    private Boolean isMale ;
    @TableField("id")
    private String idNumber ;
    @TableField("doctor_name")
    private String doctorName ;
    @TableField("in_hospital_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inTime ;

    @TableField("out_hospital_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime outTime ;

    private String sid ;
    private String wid ;
}
