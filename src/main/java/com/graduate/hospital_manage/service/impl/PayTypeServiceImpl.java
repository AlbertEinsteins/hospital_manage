package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.PayTypeMapper;
import com.graduate.hospital_manage.model.PayType;
import com.graduate.hospital_manage.service.PayTypeService;
import org.springframework.stereotype.Service;

@Service
public class PayTypeServiceImpl extends ServiceImpl<PayTypeMapper, PayType>
    implements PayTypeService {
}
