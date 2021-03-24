package com.graduate.hospital_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.graduate.hospital_manage.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService extends IService<User> {

    IPage<Map> pageResult(Page<User> userPage, User data);

    Optional<User> findByUsername(String username) ;
}
