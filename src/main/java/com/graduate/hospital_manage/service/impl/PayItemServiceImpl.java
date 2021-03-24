package com.graduate.hospital_manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.hospital_manage.mapper.PayItemMapper;
import com.graduate.hospital_manage.model.PayItem;
import com.graduate.hospital_manage.service.PayItemService;
import org.springframework.stereotype.Service;

@Service
public class PayItemServiceImpl extends ServiceImpl<PayItemMapper, PayItem>
    implements PayItemService {
}
