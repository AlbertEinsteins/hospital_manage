package com.graduate.hospital_manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.hospital_manage.model.DoctorType;
import com.graduate.hospital_manage.model.SickBed;

import java.util.List;
import java.util.Map;

public interface SickBedMapper extends BaseMapper<SickBed> {
    List<Map> selectUsedByWidWithCount();
}
