package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("role")
@Getter
@Setter
@Accessors(chain = true)
public class Role implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer rid ;
    private String code ;
    private String name ;
}
