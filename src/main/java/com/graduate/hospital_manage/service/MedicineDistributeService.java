package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.MedicineDistribution;

public interface MedicineDistributeService extends IService<MedicineDistribution> {
    void saveAndDeStock(MedicineDistribution medicineDistribution);

}
