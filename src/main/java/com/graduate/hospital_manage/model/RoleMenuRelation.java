package com.graduate.hospital_manage.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@TableName("role_menu_relation")
@Getter
@Setter
@Accessors(chain = true)
public class RoleMenuRelation {

    @TableId
    @TableField("role_id")
    private Integer rid ;

    @TableId
    @TableField("menu_id")
    private Integer menuId ;
}
