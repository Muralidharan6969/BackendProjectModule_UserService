package com.example.backendprojectmodule_userservice.Security.Models;

import com.example.backendprojectmodule_userservice.Models.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
public class JPAGrantedAuthority implements GrantedAuthority {
//    private Role role;
    private String authority;

    public JPAGrantedAuthority() {}

    public JPAGrantedAuthority(Role role) {
        this.authority = role.getRoleName();
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
