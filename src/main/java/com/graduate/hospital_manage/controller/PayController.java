package com.graduate.hospital_manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.PayDto;
import com.graduate.hospital_manage.exception.MessageException;
import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.model.Pay;
import com.graduate.hospital_manage.model.constant.PayStatus;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.response.ResultCode;
import com.graduate.hospital_manage.service.EnHospitalizedService;
import com.graduate.hospital_manage.service.OutHospitalizedService;
import com.graduate.hospital_manage.service.PayService;
import com.graduate.hospital_manage.service.PayTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private PayService payService ;

    @Autowired
    private PayTypeService payTypeService ;

    @Autowired
    private EnHospitalizedService enHospitalizedService ;

    @Autowired
    private OutHospitalizedService outHospitalizedService;

    @PostMapping("/pre_pay")
    public Result enrollRecord(@RequestBody Pay pay) {

        //TODO 预缴费
        Optional<EnHospitalized> optional = this.enHospitalizedService
                .findOneById(pay.getHid());

        if(!optional.isPresent()) {
            return Result.FAILURE("住院号不存在!") ;
        }

        pay.setStatus(PayStatus.PRE_PAY.status()) ;
        Optional<Pay> byHid = this.payService.findByHid(pay.getHid());

        if (byHid.isPresent()) {
            Pay existPay = byHid.get();
            existPay.setPreAmount(existPay.getPreAmount().add(pay.getPreAmount())) ;
            this.payService.updateById(existPay) ;
        }
        else {
            this.payService.save(pay) ;
        }

        return Result.SUCCESS();
    }

    @PostMapping("/record_search")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result recordSearch(@RequestBody PayDto queryVo) {
        return Result.SUCCESS(this.payService.pageResult(
                new Page<>(queryVo.getPagenum(), queryVo.getPagesize()), queryVo.getData())) ;
    }

    @GetMapping("/pay_items")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result payItems(@RequestParam("hid") String hid) throws Exception {
        this.enHospitalizedService.findOneById(hid)
                .orElseThrow(() -> new MessageException(ResultCode.Fail, "该住院号不存在")) ;

        this.outHospitalizedService.findOnebyHid(hid)
                .orElseThrow(() -> new MessageException(ResultCode.Fail, "该用户还未进行出院登记")) ;


        return Result.SUCCESS(this.payService.getCostByHid(hid)) ;
    }

    @GetMapping("pay_rtnitems")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result payRtnItems(@RequestParam("hid") String hid) {
        return Result.SUCCESS(this.payService.getRtnByHid(hid)) ;
    }


    @GetMapping("/get_prepay")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getPrepay(@RequestParam("hid") String hid) {

        LambdaQueryWrapper<Pay> wrapper = Wrappers.<Pay>lambdaQuery()
                .eq(Pay::getHid, hid);

        return Result.SUCCESS(this.payService.list(wrapper)) ;
    }




    @GetMapping("/paytypes")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getPayTypes() {
        return Result.SUCCESS(this.payTypeService.list()) ;
    }

}
