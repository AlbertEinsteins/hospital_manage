package com.graduate.hospital_manage.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.OutHospitalDto;
import com.graduate.hospital_manage.exception.MessageException;
import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.model.OutHospitalized;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.response.ResultCode;
import com.graduate.hospital_manage.service.EnHospitalizedService;
import com.graduate.hospital_manage.service.OutHospitalizedService;
import com.graduate.hospital_manage.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/out")
public class OutHospitalizedController {

    @Autowired
    private EnHospitalizedService enHospitalizedService ;
    @Autowired
    private OutHospitalizedService outHospitalizedService ;

    @Autowired
    private PayService payService ;

    @PostMapping("/enroll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public Result outEnroll(@RequestParam("hid") String hid) throws MessageException {
        //判断是否存在
        this.enHospitalizedService
                .findOneById(hid).orElseThrow(() ->
                    new MessageException(ResultCode.Fail, "该住院号不存在")) ;

        Optional<OutHospitalized> optional = this.outHospitalizedService.findOnebyHid(hid) ;
        if (optional.isPresent()) {
            return Result.FAILURE("该用户已经出院，若未结账，请前往结账") ;
        }

        //保存出院记录，并生成订单
        this.outHospitalizedService.saveOut(hid) ;
        return Result.SUCCESS() ;
    }

    @PostMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result pageResult(@RequestBody OutHospitalDto queryVo) {
        IPage<Map> pageResult = this.outHospitalizedService.getPageResult(new Page<OutHospitalized>(queryVo.getPagenum(), queryVo.getPagesize()),
                queryVo);
        return Result.SUCCESS(pageResult) ;
    }
}
