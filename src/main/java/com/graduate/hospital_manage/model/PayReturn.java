package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("pay_rtn")
@Getter
@Setter
@Accessors(chain = true)
public class PayReturn implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    private String id ;

    @TableField("item_name")
    private String itemName ;
    @TableField("item_amount")
    private Integer itemAmount ;
    private String pid ;
}
