package com.graduate.hospital_manage.controller;

import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.model.MedicineReturn;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.EnHospitalizedService;
import com.graduate.hospital_manage.service.MedicineReturnService;
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

    @PostMapping("/return")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public Result returnMedicine(@RequestBody MedicineReturn mr) {
        Optional<EnHospitalized> optional = this.enHospitalizedService
                .findOneById(mr.getHid()) ;
        if(!optional.isPresent()) {
            return Result.FAILURE("该住院号不存在!") ;
        }

        this.mrService.saveAndReturn(mr) ;
        return Result.SUCCESS() ;
    }
}
