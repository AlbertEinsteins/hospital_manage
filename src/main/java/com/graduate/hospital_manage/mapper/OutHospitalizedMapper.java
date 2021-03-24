package com.graduate.hospital_manage.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.graduate.hospital_manage.dto.OutHospitalDto;
import com.graduate.hospital_manage.model.OutHospitalized;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


public interface OutHospitalizedMapper extends BaseMapper<OutHospitalized> {
    IPage<Map> selectPageResult(IPage<OutHospitalized> page,
                                @Param("ex") OutHospitalDto queryVo);
}
