package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.Ward;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

public interface WardService extends IService<Ward> {

    void addWardWithSickBed(Ward ward) ;

    Optional<Ward> findById(String wid) ;

    //TODO
    IPage<Ward> pageResult(Integer pagenum, Integer pagesize) ;

    List<Ward> findByExample(Ward example) ;
}
