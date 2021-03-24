package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.MedicineReturnMapper;
import com.graduate.hospital_manage.model.Medicine;
import com.graduate.hospital_manage.model.MedicineReturn;
import com.graduate.hospital_manage.service.MedicineReturnService;
import com.graduate.hospital_manage.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicineReturnServiceImpl extends ServiceImpl<MedicineReturnMapper, MedicineReturn>
    implements MedicineReturnService {

    @Autowired
    private MedicineService medicineService ;

    @Transactional
    @Override
    public void saveAndReturn(MedicineReturn mr) {
        this.medicineService.findByName(mr.getMedicineName())
                .ifPresent(medicine -> {
                    this.save(mr) ;

                    this.medicineService.updateStock(
                            medicine.getMid(), - mr.getRtnAmount()) ;
                });
    }
}
