package com.graduate.hospital_manage.model.constant;

public enum ERole {
    /**
     * rid要和数据库对应
     */
    ROLE_ADMIN(1, "ROLE_ADMIN", "系统管理员"),
    ROLE_DOCTOR(2, "ROLE_DOCTOR", "住院医师"),
    ROLE_NURSE(4, "ROLE_NURSE", "护士"),
    ROLE_FRONT(3, "ROLE_FRONT", "服务前台"),
    ROLE_PATIENT(5, "ROLE_PATIENT", "患者") ;

    int rid ;
    String name ;
    String desc ;

    public int rid() {
        return this.rid ;
    }

    ERole(Integer rid, String name, String desc) {
        this.rid = rid ;
        this.name = name ;
        this.desc = desc ;
    }

    public static String getRoleName(Integer rid) {
        for (ERole role : ERole.values()) {
            if(role.rid == rid) {
                return role.name ;
            }
        }
        return null ;
    }
}
