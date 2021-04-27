package com.graduate.hospital_manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.dto.MedicineDto;
import com.graduate.hospital_manage.model.Medicine;
import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.MedicineService;
import com.graduate.hospital_manage.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/med")
public class MedicineController {

    @Autowired
    private MedicineService medicineService ;

    @Autowired
    private LogUtils logUtils ;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Result addMedicine(@RequestBody Medicine medicine) {
        this.medicineService.inStock(medicine) ;

        this.logUtils.writeLog(ELogLevel.INFO, "药品入库", String.format("药品：%s, 录入%d个",
                medicine.getName(), medicine.getAmount()));
        return Result.SUCCESS() ;
    }

    @PostMapping("/page")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result pageResult(@RequestBody MedicineDto queryVo) {
        QueryWrapper<Medicine> query = Wrappers.query();

        if(!StringUtils.isEmpty(queryVo.getData().getInTime())) {
            query.ge("in_time", queryVo.getData().getInTime()) ;
        }
        if(StringUtils.hasLength(queryVo.getData().getMid())) {
            query.eq("mid", queryVo.getData().getMid()) ;
        }
        if(StringUtils.hasLength(queryVo.getData().getName())) {
            query.like("name", queryVo.getData().getName()) ;
        }

        IPage<Medicine> result = this.medicineService.page(new Page<>(queryVo.getPagenum(), queryVo.getPagesize()),
                query);
        return Result.SUCCESS(result) ;
    }
}
