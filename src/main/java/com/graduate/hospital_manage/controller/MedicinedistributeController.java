package com.graduate.hospital_manage.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Maps;
import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.model.MedicineDistribution;
import com.graduate.hospital_manage.model.constant.ELogLevel;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.EnHospitalizedService;
import com.graduate.hospital_manage.service.MedicineDistributeService;
import com.graduate.hospital_manage.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/med_dis")
public class MedicinedistributeController {

    @Autowired
    private MedicineDistributeService medicineDistributeService;

    @Autowired
    private EnHospitalizedService enHospitalizedService;

    @Autowired
    private LogUtils logUtils ;

    @PostMapping("/dis")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public Result distributeMedicine(@RequestBody MedicineDistribution medicineDistribution) {
        Optional<EnHospitalized> optional = this.enHospitalizedService
                .findOneById(medicineDistribution.getHid());

        if (!optional.isPresent()) {
            return Result.FAILURE("住院号不存在!");
        }

        EnHospitalized hospitalized = optional.get();
        if(!hospitalized.getIsActive()) {
            return Result.FAILURE("该病人已经出院") ;
        }
        this.logUtils.writeLog(ELogLevel.INFO, "药品分发", String.format("%s: %s瓶/个 %s",
                hospitalized.getName(), medicineDistribution.getName(), medicineDistribution.getDisAmount())) ;
        this.medicineDistributeService.saveAndDeStock(medicineDistribution);
        return Result.SUCCESS();
    }

    /**
     * 获取所有正在住院者的 (姓名--住院号)
     *
     * @return
     */
    @GetMapping("/gethids")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'FRONT', 'NURSE')")
    public Result getHidsByName() {
        LambdaQueryWrapper<EnHospitalized> wrapper = Wrappers.lambdaQuery();
        wrapper.select(EnHospitalized::getHid, EnHospitalized::getName)
                .eq(EnHospitalized::getIsActive, 1);

        List<HashMap<String, Object>> res = this.enHospitalizedService.list(wrapper)
                .stream().map(item -> {
                    HashMap<String, Object> itemRs = Maps.newHashMap();
                    itemRs.put("hid", item.getHid());
                    itemRs.put("name", item.getName());
                    return itemRs;
                }).collect(Collectors.toList());

        return Result.SUCCESS(res);
    }
}
