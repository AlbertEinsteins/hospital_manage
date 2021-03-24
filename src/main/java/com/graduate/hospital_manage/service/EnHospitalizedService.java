package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.dto.EnHospitalizedDto;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.model.EnHospitalized;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EnHospitalizedService extends IService<EnHospitalized> {
    //病人统计
    List<Map> countPatientByDoctypes(LocalDate from, LocalDate to) ;
    //病房统计
    List<Map> countWardByDocTypes(Integer tid) ;


    Optional<EnHospitalized> findOneByIdCardInActive(String idNumber) ;

    Optional<EnHospitalized> findOneById(String hid) ;

    //修改id对应的status
    void updateActiveStatusById(String hid) ;

    //分页查询入院记录
    IPage<Map> pageResultByExample(Page<EnHospitalized> page, EnHospitalizedDto queryVo) ;

    //根据hid 查询医生信息
    Optional<Doctor> findByHid(String hid) ;

    void saveWithSickBed(EnHospitalized enHospitalized);
}
