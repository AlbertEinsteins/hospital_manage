package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("sickbed")
@Getter
@Setter
@Accessors(chain = true)
public class SickBed implements Serializable {
    @TableId
    private String sid ;

    private String wid ;

    private Integer status ;
}
