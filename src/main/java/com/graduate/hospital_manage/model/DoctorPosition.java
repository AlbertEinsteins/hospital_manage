package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("doctor_position")
@Getter
@Setter
@Accessors(chain = true)
public class DoctorPosition implements Serializable {
    @TableId
    private Integer pid ;
    private String code ;
    private String name ;
}
