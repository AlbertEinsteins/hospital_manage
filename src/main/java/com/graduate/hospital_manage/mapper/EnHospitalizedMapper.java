package com.graduate.hospital_manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.EnHospitalizedDto;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.model.EnHospitalized;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface EnHospitalizedMapper extends BaseMapper<EnHospitalized> {

    /**
     * 条件查询， 关联(doctor_type)并分页
     */
    IPage<Map> selectByEnhospitalCondition(Page<EnHospitalized> page, @Param("example") EnHospitalizedDto example) ;

    //根据hid 查询医生姓名
    Doctor selectDoctorByHid(@Param("hid") String hid) ;

    List<Map> selectStatisticInByDocTypes(@Param("fromDate") LocalDate fromDate,
                                          @Param("toDate") LocalDate toDate);
    List<Map> selectStatisticOutByDocTypes(@Param("fromDate") LocalDate fromDate,
                                           @Param("toDate") LocalDate toDate);

    //查询病房的使用情况
    List<Map> selectStatisticOfWard(@Param("tid") Integer tid) ;
}
