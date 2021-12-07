package com.QCINE.Main.payload.response;

import java.util.List;

public class JwtResponse {
    private long id;

    private String username;

    private String name;

    private String email;

    private String idrap;

    private List<String>  roles;

    private String type = "Bearer";

    private String token;

    public JwtResponse(long id, String username, String name, String email, String idrap, List<String> roles, String token) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.idrap = idrap;
        this.roles = roles;
        this.type = type;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdrap() {
        return idrap;
    }

    public void setIdrap(String idrap) {
        this.idrap = idrap;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
