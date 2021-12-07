package com.QCINE.Main.DTO;

public class User_DTO {
    private int idUser;
    private String email;
    private String username;
    private String password;
    private int roleEntityIdRole;
    private int customerEntityIdCustomer;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleEntityIdRole() {
        return roleEntityIdRole;
    }

    public void setRoleEntityIdRole(int roleEntityIdRole) {
        this.roleEntityIdRole = roleEntityIdRole;
    }

    public int getCustomerEntityIdCustomer() {
        return customerEntityIdCustomer;
    }

    public void setCustomerEntityIdCustomer(int customerEntityIdCustomer) {
        this.customerEntityIdCustomer = customerEntityIdCustomer;
    }
}
