package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("log")
@Getter
@Setter
public class Systemlog implements Serializable {
    @TableId
    private Integer id ;
    private String type ;
    @TableField("happen_time")
    private LocalDateTime happenTime ;
    private String describe ;
    private String operate ;
}
