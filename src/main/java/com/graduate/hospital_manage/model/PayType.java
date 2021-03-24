package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@TableName("pay_type")
@Getter
@Setter
@Accessors(chain = true)
public class PayType {
    @TableId
    private Integer id ;
    private String code ;
    private String name ;
}
