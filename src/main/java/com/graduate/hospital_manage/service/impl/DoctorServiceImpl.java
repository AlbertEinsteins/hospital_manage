package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.DoctorMapper;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.service.DoctorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor>
    implements DoctorService {

    @Override
    public List<Doctor> findByExample(Doctor example) {
        return this.list(Wrappers.query(example)) ;
    }

    @Override
    public IPage<Map> getPageByExample(Doctor cond, Page<Doctor> page ) {
        return getBaseMapper().selectDoctorsWithPos(page, cond) ;
    }

    @Override
    public IPage<Map> findByUsername(String username, Integer isActive, Page<Map> page) {
        return getBaseMapper().selectPatientsWithDoctorusername(page, username, isActive);
    }

    @Override
    public IPage<Map> findByUsername(String hid,
                                     String username,
                                     LocalDateTime startTime,
                                     LocalDateTime endTime, Page<Map> page) {
        return getBaseMapper().selectPatientsInActiveWithDoctorusername(page, username, hid, startTime, endTime);
    }
}
