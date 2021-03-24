package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.Medicine;

import java.util.Optional;

public interface MedicineService extends IService<Medicine> {
    void inStock(Medicine medicine);

    Optional<Medicine> findById(String mid) ;

    Optional<Medicine> findByName(String name) ;

    void updateStock(String mid, Integer amount) ;

}
