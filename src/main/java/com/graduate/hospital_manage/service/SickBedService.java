package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.SickBed;
import com.graduate.hospital_manage.model.WardType;

import java.util.List;
import java.util.Map;

public interface SickBedService extends IService<SickBed> {
    void updateStatus(String sid, int status);

}
