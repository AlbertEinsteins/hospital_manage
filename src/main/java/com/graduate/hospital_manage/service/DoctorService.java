package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.model.EnHospitalized;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface DoctorService extends IService<Doctor> {
    List<Doctor> findByExample(Doctor example) ;

    IPage<Map> getPageByExample(Doctor cond, Page<Doctor> page);


    //根据用户名查询该医生当前所负责的病人的信息
    IPage<Map> findByUsername(String username, Integer isActive, Page<Map> page);

    IPage<Map> findByUsername(String hid,
                              String username,
                              LocalDateTime startTime,
                              LocalDateTime endTime,
                              Page<Map> page);


}
