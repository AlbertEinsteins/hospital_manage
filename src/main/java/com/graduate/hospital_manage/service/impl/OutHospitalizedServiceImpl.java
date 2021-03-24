package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.dto.EnHospitalizedDto;
import com.graduate.hospital_manage.dto.OutHospitalDto;
import com.graduate.hospital_manage.mapper.OutHospitalizedMapper;
import com.graduate.hospital_manage.model.*;
import com.graduate.hospital_manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class OutHospitalizedServiceImpl extends ServiceImpl<OutHospitalizedMapper, OutHospitalized>
    implements OutHospitalizedService {
    @Autowired
    private EnHospitalizedService enHospitalizedService ;
    @Autowired
    private OutHospitalizedService outHospitalizedService ;

    @Autowired
    private DoctorService doctorService ;

    @Autowired
    private SickBedService sickBedService ;

    @Autowired
    private PayService payService ;

    @Autowired
    private PayItemService payItemService ;

    @Autowired
    private PayReturnService payReturnService ;

    @Override
    public IPage<Map> getPageResult(IPage<OutHospitalized> page, OutHospitalDto queryVo) {
        return this.getBaseMapper().selectPageResult(page, queryVo) ;
    }

    @Transactional
    @Override
    public void saveOut(String hid) {
        OutHospitalized outHospitalized = new OutHospitalized() ;

        //生成出院记录
        this.enHospitalizedService.findByHid(hid).ifPresent(doctor ->  {
            outHospitalized.setDoctorName(doctor.getName()) ;
        });

        this.enHospitalizedService.findOneById(hid).ifPresent(enHospitalized -> {
            outHospitalized.setUid(enHospitalized.getHid())
                    .setWid(enHospitalized.getWid())
                    .setSid(enHospitalized.getSid())
                    .setInTime(enHospitalized.getEnrollTime())
                    .setOutTime(LocalDateTime.now())
                    .setIdNumber(enHospitalized.getId())
                    .setIsMale(enHospitalized.getIsMale())
                    .setName(enHospitalized.getName()) ;

            this.outHospitalizedService.save(outHospitalized) ;
            //更新住院状态
            this.enHospitalizedService.updateActiveStatusById(hid);
            //回收病床
            this.sickBedService.updateStatus(outHospitalized.getSid(), 0) ;

            this.createOrderItems(hid) ;
        });
    }

    //生成支付订单
    //返还的订单
    @Transactional
    public void createOrderItems(String hid) {
        //获取床位费和医药费
        List<PayItem> costByHid = this.payService.getCostByHidForCreateOrder(hid) ;

        //返还的药费
        List<PayReturn> rtnByHid = this.payService.getRtnByHidForCreateOrder(hid) ;

        //获取预支付的订单号
        this.payService.findByHid(hid).ifPresent(pay -> {
            costByHid.forEach(item -> {
                item.setPid(pay.getPid()) ;
            });
            rtnByHid.forEach(rtn -> {
                rtn.setPid(pay.getPid()) ;
            });

            this.payItemService.saveBatch(costByHid) ;
            this.payReturnService.saveBatch(rtnByHid) ;
        });
    }

    @Override
    public Optional<OutHospitalized> findOnebyHid(String hid) {
        return Optional.ofNullable(this.getById(hid)) ;
    }
}
