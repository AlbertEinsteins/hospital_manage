package com.graduate.hospital_manage.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.graduate.hospital_manage.model.User;
import com.graduate.hospital_manage.model.constant.ERole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserDetailImpl implements UserDetails {

    private Integer rid ;

    private String username ;

    @JsonIgnore
    private String password ;

    private Collection<? extends GrantedAuthority> authorities ;


    public UserDetailImpl(Integer rid, String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.rid = rid;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }


    public static UserDetailImpl build(User user) {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(ERole.getRoleName(user.getRid())) ;

        List<GrantedAuthority> authorities = Collections.singletonList(authority);
        return new UserDetailImpl(
                user.getRid(),
                user.getUsername(),
                user.getPassword(),
                authorities) ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return false;
        if(obj == null || getClass() != obj.getClass())
            return false;
        UserDetailImpl user = (UserDetailImpl) obj ;
        return Objects.equals(username, user.username) ;
    }
}
