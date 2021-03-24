package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.dto.OutHospitalDto;
import com.graduate.hospital_manage.model.OutHospitalized;

import java.util.Map;
import java.util.Optional;


public interface OutHospitalizedService extends IService<OutHospitalized> {

    void saveOut(String hid) ;

    IPage<Map> getPageResult(IPage<OutHospitalized> page, OutHospitalDto queryVo) ;

    //判断是否已经登记出院
    Optional<OutHospitalized> findOnebyHid(String hid) ;
}
