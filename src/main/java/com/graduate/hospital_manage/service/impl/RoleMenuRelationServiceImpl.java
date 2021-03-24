package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.RoleMenuRelationMapper;
import com.graduate.hospital_manage.model.RoleMenuRelation;
import com.graduate.hospital_manage.service.RoleMenuRelationService;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuRelationServiceImpl extends ServiceImpl<RoleMenuRelationMapper, RoleMenuRelation>
    implements RoleMenuRelationService {
}
