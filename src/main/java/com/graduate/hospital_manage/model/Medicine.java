package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("medicine")
@Getter
@Setter
@Accessors(chain = true)
public class Medicine implements Serializable {
    @TableId
    @NotBlank
    private String mid ;
    @NotBlank
    private String name ;
    private Integer amount ;
    private BigDecimal price ;

    @TableField("produce_time")
    private LocalDate produceTime ;
    @TableField("end_time")
    private LocalDate endTime ;
    @TableField("in_time")
    private LocalDate inTime ;
    private String producer ;
    private String supplier ;

    @TableField("dis_amount")
    private Integer disAmount ;

}
