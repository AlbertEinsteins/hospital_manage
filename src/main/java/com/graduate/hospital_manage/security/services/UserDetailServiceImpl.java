package com.graduate.hospital_manage.security.services;

import com.graduate.hospital_manage.model.User;
import com.graduate.hospital_manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService ;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userService.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("用户名不存在")) ;

        return UserDetailImpl.build(user) ;
    }
}
