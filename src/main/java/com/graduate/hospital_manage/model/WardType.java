package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("ward_type")
@Getter
@Setter
@Accessors(chain = true)
public class WardType implements Serializable {
    @TableId
    private Integer id ;
    private String code ;
    private String name ;
    private BigDecimal price ;

    @TableField("update_time")
    private LocalDateTime updateTime ;
}
