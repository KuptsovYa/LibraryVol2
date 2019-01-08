package com.example.LibraryVol2.dto;

import org.springframework.security.core.GrantedAuthority;

public class RoleDto implements GrantedAuthority {
    private int id;
    private String role;

    public RoleDto() {
    }

    public RoleDto(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
