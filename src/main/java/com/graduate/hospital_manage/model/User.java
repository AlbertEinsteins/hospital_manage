package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("user")
@Getter
@Setter
@Accessors(chain = true)
public class User implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    @NotBlank
    private String uid ;

    @NotBlank
    @Size(max = 64)
    private String fullname ;

    @NotBlank
    @Size(max = 64)
    private String username ;

    @NotBlank
    @Size(max = 64)
    private String password ;
    private BigDecimal amount ;

    @NotNull
    private Integer rid ;

    @Size(max = 32)
    private String phone ;

    private LocalDateTime registTime ;
}
