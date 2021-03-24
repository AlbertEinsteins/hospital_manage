package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.MedicineMapper;
import com.graduate.hospital_manage.model.Medicine;
import com.graduate.hospital_manage.service.MedicineService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine>
    implements MedicineService {
    @Override
    public Optional<Medicine> findById(String mid) {
        return Optional.ofNullable(this.getById(mid)) ;
    }

    @Override
    public void inStock(Medicine medicine) {
        Optional<Medicine> optional = this.findById(medicine.getMid()) ;
        if(optional.isPresent()) {
            Medicine exist = optional.get();
            exist.setAmount(exist.getAmount() + medicine.getAmount()) ;
            this.updateById(exist) ;
            return ;
        }

        this.save(medicine) ;
    }

    @Override
    public Optional<Medicine> findByName(String name) {

        return Optional.ofNullable(
                this.getOne(
                        Wrappers.<Medicine>lambdaQuery().eq(Medicine::getName, name),
                        false)) ;
    }

    @Override
    public void updateStock(String mid, Integer amount) {
        this.findById(mid).ifPresent(medicine -> {
            Medicine update = new Medicine() ;
            update.setMid(medicine.getMid()) ;
            update.setDisAmount(medicine.getDisAmount() + amount) ;

            this.updateById(update) ;
        });
    }

}
