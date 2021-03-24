package com.graduate.hospital_manage.controller;

import com.graduate.hospital_manage.dto.StatisticDto;
import com.graduate.hospital_manage.model.EnHospitalized;
import com.graduate.hospital_manage.response.Result;
import com.graduate.hospital_manage.service.EnHospitalizedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private EnHospitalizedService enHospitalizedService ;


    @PostMapping("/patient")
    public Result patientStatistic(@RequestBody StatisticDto statisticDto) {
        return Result.SUCCESS(
                this.enHospitalizedService
                        .countPatientByDoctypes(statisticDto.getFromDate(), statisticDto.getToDate()));
    }

    @PostMapping("/ward")
    public Result wardStatistic(@RequestBody StatisticDto statisticDto) {
        return Result.SUCCESS(
                this.enHospitalizedService
                        .countWardByDocTypes(statisticDto.getTid())
        ) ;
    }
}
