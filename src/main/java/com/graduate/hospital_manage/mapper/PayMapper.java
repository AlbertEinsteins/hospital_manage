package com.graduate.hospital_manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduate.hospital_manage.model.Dict;
import com.graduate.hospital_manage.model.Pay;
import com.graduate.hospital_manage.model.PayItem;
import com.graduate.hospital_manage.model.PayReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PayMapper extends BaseMapper<Pay> {

    List<PayItem> selectWardCostByHid(@Param("hid") String hid) ;

    //医药费
    List<PayItem> selectMedicineCost(@Param("hid") String hid);

    //返还的医药费
    List<PayReturn> selectRtnMoney(@Param("hid") String hid);

    IPage<Map> selectPagePayRecord(Page<Pay> objectPage, @Param("pay") Pay queryVo);
}
