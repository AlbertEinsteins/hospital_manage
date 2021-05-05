package com.graduate.hospital_manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.graduate.hospital_manage.model.Ward;
import com.graduate.hospital_manage.model.WardType;

import java.util.List;

public interface WardMapper extends BaseMapper<Ward> {
    List<Ward> selectAvailableWards();
}
