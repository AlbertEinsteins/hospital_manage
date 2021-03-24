package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.SickBed;
import com.graduate.hospital_manage.model.WardType;

public interface SickBedService extends IService<SickBed> {
    void updateStatus(String sid, int status);
}
