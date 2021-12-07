package com.QCINE.Main.security.service;

import com.QCINE.Main.Entity.User_Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private int id;

    private String username;

    private String name;

    private String email;

    private String idrap;

    @JsonIgnore
    private String password;

    public Collection<? extends GrantedAuthority> Authorities;

    public UserDetailsImpl() {
    }

    public UserDetailsImpl(int Id ,String username, String name, String email, String idrap,
                           String password,Collection<? extends GrantedAuthority> Authorities) {
        this.id = Id;
        this.username = username;
        this.name= name;
        this.email = email;
        this.idrap = idrap;
        this.password = password;
        this.Authorities = Authorities;
    }


    public static UserDetailsImpl build(User_Entity user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getIdUser(),
                user.getUsername(),
                user.getName(),
                user.getEmail(),
                user.getIdRap(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public String getIdrap() { return idrap; }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}

