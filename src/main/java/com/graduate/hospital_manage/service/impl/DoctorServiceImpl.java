package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.DoctorMapper;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.service.DoctorService;
import org.springframework.stereotype.Service;

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
    public IPage<Doctor> pageByExample(Doctor cond, Page<Doctor> page) {
        return null;
    }

    @Override
    public IPage<Map> getPageByExample(Doctor cond, Page<Doctor> page ) {
        return this.getBaseMapper().selectDoctorsWithPos(page, cond) ;
    }
}