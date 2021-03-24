package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.PayReturnMapper;
import com.graduate.hospital_manage.model.PayReturn;
import com.graduate.hospital_manage.service.PayReturnService;
import org.springframework.stereotype.Service;

@Service
public class PayReturnServiceImpl extends ServiceImpl<PayReturnMapper, PayReturn>
    implements PayReturnService {
}
