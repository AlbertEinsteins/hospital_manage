package com.graduate.hospital_manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.model.EnHospitalized;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.Map;

public interface DoctorMapper extends BaseMapper<Doctor> {

    IPage<Map> selectDoctorsWithPos(Page<Doctor> p, Doctor doctor) ;


    IPage<Map> selectPatientsWithDoctorusername(Page<Map> page,
                                                           @Param("username") String username,
                                                           @Param("isActive") Integer isActive);
    IPage<Map> selectPatientsInActiveWithDoctorusername(Page<Map> page,
                                                           @Param("username") String username,
                                                           @Param("hid") String hid,
                                                           @Param("startTime") LocalDateTime startTime,
                                                           @Param("endTime") LocalDateTime endTime);

}
