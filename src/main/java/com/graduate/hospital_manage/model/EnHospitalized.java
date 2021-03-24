package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("hospitalized")
@Setter
@Getter
@Accessors(chain = true)
public class EnHospitalized implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    private String hid ;

    @NotBlank
    private String name ;

    @NotBlank
    private String id ;
    @TableField("nation_id")
    private Integer nationId ;
    @TableField("sex")
    private Boolean isMale ;
    @TableField("workaddress")
    private String workAddress ;
    private LocalDate birthday ;
    private String address ;
    private String phone ;
    @TableField("is_married")
    private Boolean isMarried ;
    @TableField("body_status")
    private String bodyStatus ;
    @TableField("emerge_name")
    private String emergeName ;
    @TableField("emerge_phone")
    private String emergePhone ;
    private Integer did ;
    private String wid ;
    private String sid ;

    @TableField("enroll_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime enrollTime ;

    @TableField("is_active")
    private Boolean isActive ;
}
