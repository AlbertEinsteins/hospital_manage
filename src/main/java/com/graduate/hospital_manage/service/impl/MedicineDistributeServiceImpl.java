package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.MedicineDistributeMapper;
import com.graduate.hospital_manage.model.MedicineDistribution;
import com.graduate.hospital_manage.service.MedicineDistributeService;
import com.graduate.hospital_manage.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicineDistributeServiceImpl extends ServiceImpl<MedicineDistributeMapper, MedicineDistribution>
    implements MedicineDistributeService {

    @Autowired
    private MedicineService medicineService ;

    @Transactional
    @Override
    public void saveAndDeStock(MedicineDistribution medicineDistribution) {
        this.save(medicineDistribution) ;
        this.medicineService.updateStock(medicineDistribution.getMid(),
                medicineDistribution.getDisAmount());
    }
}
