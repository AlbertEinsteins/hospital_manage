package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("log")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Log implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id ;

    @TableField("type_id")
    private Integer typeId ;

    @TableField("happen_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime happenTime ;

    private String describ ;   //具体的日志描述
    private String operate ;    //操作状态，可以为空

    public static Log create(Integer typeId, String describe, String operate) {
        return new Log().setTypeId(typeId)
                .setHappenTime(LocalDateTime.now())
                .setDescrib(describe)
                .setOperate(operate) ;
    }
}
