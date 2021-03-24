package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@TableName("medicine_return")
@Getter
@Setter
@Accessors(chain = true)
public class MedicineReturn implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    private String rid ;
    private String name ;
    @NotNull
    @TableField("medicine_name")
    private String medicineName ;

    @TableField("rtn_amount")
    private Integer rtnAmount ;
    private String reason ;
    @NotNull
    private String hid ;
}
