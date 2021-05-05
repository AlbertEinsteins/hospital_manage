package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.Doctor;

import java.util.List;
import java.util.Map;

public interface DoctorService extends IService<Doctor> {
    List<Doctor> findByExample(Doctor example) ;

    IPage<Map> getPageByExample(Doctor cond, Page<Doctor> page) ;

}
