package com.graduate.hospital_manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.EnHospitalizedDto;
import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.EnHospitalizedService;
import com.graduate.hospital_manage.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/en")
public class EnHospitalizedController {
    @Autowired
    private LogUtils logUtils ;

    @Autowired
    private EnHospitalizedService enHospitalizedService ;

    @PostMapping("/enroll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public Result enRollHospital(@Valid @RequestBody EnHospitalized enHospitalized) {

        Optional<EnHospitalized> existItem =
                this.enHospitalizedService.findOneByIdCardInActive(enHospitalized.getId());

        if(!existItem.isPresent()) {
            enHospitalized.setIsActive(true) ;

            this.enHospitalizedService.saveWithSickBed(enHospitalized) ;

            logUtils.writeLog(ELogLevel.INFO, "用户住院登记", enHospitalized.getName());
            return Result.SUCCESS() ;
        }

        return Result.FAILURE("id已经存在") ;
    }

    @PostMapping("/page")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN', 'NURSE', 'FRONT')")
    public Result page(@RequestBody EnHospitalizedDto queryVo) {
        IPage<Map> pageData = this.enHospitalizedService.pageResultByExample(new Page<>(queryVo.getPagenum(), queryVo.getPagesize()),
                queryVo);
        return Result.SUCCESS(pageData) ;
    }
}
