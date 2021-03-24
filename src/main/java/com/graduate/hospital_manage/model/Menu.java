package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@TableName("menu")
@Getter
@Setter
public class Menu implements Serializable {
    @TableId
    @TableField("menu_id")
    private Integer menuId ;
    private String name ;
    private String url ;
    private Integer pid ;
    private Integer orders ;
    private String icon ;

    @JsonIgnore
    @TableField(exist = false)
    private Integer rid ;
}
