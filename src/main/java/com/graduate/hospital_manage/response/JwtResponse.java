package com.graduate.hospital_manage.response;

import java.io.Serializable;
import java.util.List;

public class JwtResponse implements Serializable {
    private String token ;
    private String type = "Bearer" ;
    private Integer rid ;
    private String username ;
    private List<String> roles ;

    public JwtResponse(String accessToken, Integer rid, String username, List<String> roles) {
        this.token = accessToken;
        this.type = type;
        this.rid = rid;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
