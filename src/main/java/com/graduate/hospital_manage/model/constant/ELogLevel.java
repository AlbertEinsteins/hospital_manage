package com.graduate.hospital_manage.model.constant;

public enum ELogLevel {

    /**
     * 属性值要和数据库字典表对应
     */
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR") ;


    String level ;
    ELogLevel(String level) {
        this.level = level ;
    }
}
