package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.WardType;

public interface WardTypeService extends IService<WardType> {

    IPage<WardType> pageResult(Integer pagenum, Integer pagesize) ;

    void updatePrice(Integer id, Integer price) ;
}
