package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.Dict;

import java.util.List;


public interface DictService extends IService<Dict> {

    List<Dict> findAllNations() ;

    List<Dict> findAllEventTypes() ;
}
