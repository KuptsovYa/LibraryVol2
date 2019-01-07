package com.example.LibraryVol2.service;

import com.example.LibraryVol2.dto.Roles;
import com.example.LibraryVol2.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

    private final UsersEntity usersEntity;

    @Autowired
    public UserDetailsImpl(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = this.usersEntity.getRolesByIdusers().getRole();
        Set<Roles> roles = new HashSet<>();
        if(role.equals(Roles.ADMIN.name())){
            roles.add(Roles.ADMIN);
        }else {
            roles.add(Roles.USER);
        }
        return roles;
    }

    @Override
    public String getPassword() {
        return usersEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return usersEntity.getLogin();
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
}