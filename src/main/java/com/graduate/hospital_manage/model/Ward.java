package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("ward")
@Getter
@Setter
public class Ward implements Serializable {
    @TableId
    private String wid ;
    @TableField("dt_id")
    private Integer doctorTypeId ;
    @TableField("wt_id")
    private Integer wardTypeId ;
    private Integer capacity ;
    @TableField("create_time")
    private LocalDateTime createTime ;
}
