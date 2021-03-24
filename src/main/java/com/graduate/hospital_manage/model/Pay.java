package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@TableName("pay")
@Getter
@Setter
@Accessors(chain = true)
public class Pay implements Serializable {
    @TableId(type = IdType.ID_WORKER_STR)
    private String        pid ;
    @TableField("type_id")
    private Integer       typeId ;
    private String        name ;
    @TableField("pre_amount")
    private BigDecimal    preAmount ;
    @TableField("pay_amount")
    private BigDecimal    payAmount ;
    @TableField("pay_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payDate ;
    @TableField("receive_name")
    private String        receiveName ;
    private Integer       status ;
    private String        hid ;
    @TableField("doc_name")
    private String docName ;

    private Integer tid ;

    @TableField("receive_account")
    private String receiveUsername ;
}
