package com.graduate.hospital_manage.controller;

import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.model.MedicineReturn;
import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.EnHospitalizedService;
import com.graduate.hospital_manage.service.MedicineReturnService;
import com.graduate.hospital_manage.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("med_rtn")
public class MedicineReturnController {

    @Autowired
    private EnHospitalizedService enHospitalizedService ;

    @Autowired
    private MedicineReturnService mrService ;

    @Autowired
    private LogUtils logUtils ;

    @PostMapping("/return")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public Result returnMedicine(@RequestBody MedicineReturn mr) {
        Optional<EnHospitalized> optional = this.enHospitalizedService
                .findOneById(mr.getHid()) ;
        if(!optional.isPresent()) {
            return Result.FAILURE("该住院号不存在!") ;
        }

        this.mrService.saveAndReturn(mr) ;

        this.logUtils.writeLog(ELogLevel.INFO, "药品返还", String.format("返还人: %s, 数量: %d, 药品名: %s",
                mr.getName(), mr.getRtnAmount(), mr.getMedicineName()));
        return Result.SUCCESS() ;
    }
}
