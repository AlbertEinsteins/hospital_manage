package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@TableName("doctor_type")
@Getter
@Setter
public class DoctorType implements Serializable {
    @TableId
    private String tid ;
    private String code ;
    private String name ;
}
