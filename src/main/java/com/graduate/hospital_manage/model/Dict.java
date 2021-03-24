package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("dict")
@Getter
@Setter
@Accessors(chain = true)
public class Dict implements Serializable {
    private Integer id ;
    private String code ;
    private String name ;
    private String value ;
}
