package com.QCINE.Main.DTO.Filter;

public class Login_info_DTO {
    private String name;
    private String role;
    private String auth;
    private int idUser;
    private String idRap;

    public String getIdRap() {
        return idRap;
    }

    public void setIdRap(String idRap) {
        this.idRap = idRap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}