package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@TableName("doctor")
@Getter
@Setter
@Accessors(chain = true)
public class Doctor implements Serializable {
    @TableId(type = IdType.AUTO)
    @NotNull
    private Integer did ;
    private String name ;
    @TableField("hiredate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate hireDate ;
    private Boolean isMale ;
    private Integer tid ;
    private Integer pid ;
    @TableField("is_retire")
    private Boolean isRetire ;
}
