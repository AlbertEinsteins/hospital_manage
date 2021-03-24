package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.DoctorTypeMapper;
import com.graduate.hospital_manage.model.DoctorType;
import com.graduate.hospital_manage.service.DoctorTypeService;
import org.springframework.stereotype.Service;

@Service
public class DoctorTypeServiceImpl extends ServiceImpl<DoctorTypeMapper, DoctorType>
    implements DoctorTypeService {
}
