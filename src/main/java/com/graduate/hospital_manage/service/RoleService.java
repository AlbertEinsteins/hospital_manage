package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.Role;


public interface RoleService extends IService<Role> {

    boolean existRole(Integer rid) ;
}
