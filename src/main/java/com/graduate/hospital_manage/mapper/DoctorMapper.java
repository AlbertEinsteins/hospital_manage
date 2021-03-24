package com.graduate.hospital_manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.model.Doctor;

import java.util.Map;

public interface DoctorMapper extends BaseMapper<Doctor> {

    IPage<Map> selectDoctorsWithPos(Page<Doctor> p, Doctor doctor) ;
}
