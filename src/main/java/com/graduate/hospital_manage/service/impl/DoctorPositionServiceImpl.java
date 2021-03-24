package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.DoctorPositionMapper;
import com.graduate.hospital_manage.model.DoctorPosition;
import com.graduate.hospital_manage.service.DoctorPositionService;
import org.springframework.stereotype.Service;

@Service
public class DoctorPositionServiceImpl extends ServiceImpl<DoctorPositionMapper, DoctorPosition>
    implements DoctorPositionService {
}
