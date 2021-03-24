package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.graduate.hospital_manage.dto.EnHospitalizedDto;
import com.graduate.hospital_manage.mapper.EnHospitalizedMapper;
import com.graduate.hospital_manage.model.Doctor;
import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.model.SickBed;
import com.graduate.hospital_manage.service.EnHospitalizedService;
import com.graduate.hospital_manage.service.SickBedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EnHospitalizedServiceImpl extends ServiceImpl<EnHospitalizedMapper, EnHospitalized>
    implements EnHospitalizedService {

    @Autowired
    private SickBedService sickBedService ;

    @Override
    public List<Map> countWardByDocTypes(Integer tid) {
        return this.getBaseMapper().selectStatisticOfWard(tid) ;
    }

    @Override
    public List<Map> countPatientByDoctypes(LocalDate from, LocalDate to) {
        List<Map> inStatistic = this.getBaseMapper()
                .selectStatisticInByDocTypes(from, to) ;

        List<Map> outStatistic = this.getBaseMapper()
                .selectStatisticOutByDocTypes(from, to) ;

        int i = 0 ;
        int j = 0 ;
        for(; i < inStatistic.size() && j < outStatistic.size() ; ) {
            if(inStatistic.get(i).get("tid")
                    .equals(outStatistic.get(j).get("tid"))) {

                inStatistic.get(i).put("sumOfOut", outStatistic.get(j).get("sumOfOut")) ;
                i ++ ;
                j ++ ;
            }
            else i++ ;
        }


        return inStatistic ;
    }

    @Override
    public Optional<EnHospitalized> findOneByIdCardInActive(String idNumber) {
        QueryWrapper<EnHospitalized> query = Wrappers.query();
        query.eq("id", idNumber)
                .eq("is_active", true) ;
        return Optional.ofNullable(this.getOne(query,false)) ;
    }

    @Override
    public IPage<Map> pageResultByExample(Page<EnHospitalized> page, EnHospitalizedDto queryVo) {
        return this.getBaseMapper().selectByEnhospitalCondition(page, queryVo) ;
    }

    @Override
    public Optional<EnHospitalized> findOneById(String hid) {
        return Optional.ofNullable(this.getById(hid)) ;
    }

    @Override
    public void updateActiveStatusById(String hid) {
        EnHospitalized update = new EnHospitalized() ;

        update.setHid(hid).setIsActive(false) ;
        this.updateById(update) ;
    }

    @Override
    @Transactional
    public void saveWithSickBed(EnHospitalized enHospitalized) {
        this.save(enHospitalized) ;

        //分配病床
        this.sickBedService.updateStatus(enHospitalized.getSid(), 1) ;
    }

    @Override
    public Optional<Doctor> findByHid(String hid) {
        return Optional.ofNullable(this.getBaseMapper().selectDoctorByHid(hid)) ;
    }
}
