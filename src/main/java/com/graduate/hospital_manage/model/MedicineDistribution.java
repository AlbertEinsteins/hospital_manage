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
import java.math.BigDecimal;

@TableName("medicine_distribution")
@Getter
@Setter
@Accessors(chain = true)
public class MedicineDistribution implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    @NotNull
    private String did ;
    private String name ;
    @TableField("distribu_amount")
    private Integer disAmount ;
    private BigDecimal price ;
    private String mid ;
    private String hid ;
}
