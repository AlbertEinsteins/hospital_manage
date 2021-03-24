package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.RoleMapper;
import com.graduate.hospital_manage.model.Role;
import com.graduate.hospital_manage.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService {
    @Override
    public boolean existRole(Integer rid) {
        return this.getById(rid) != null ;
    }
}
