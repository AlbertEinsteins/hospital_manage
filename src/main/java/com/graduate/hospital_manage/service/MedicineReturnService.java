package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.MedicineReturn;

public interface MedicineReturnService extends IService<MedicineReturn> {

    void saveAndReturn(MedicineReturn mr) ;
}
