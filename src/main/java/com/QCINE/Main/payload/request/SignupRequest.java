package com.QCINE.Main.payload.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignupRequest {

    @NotBlank( message = "email not blank")
    @Size(min = 8,max = 50,  message = " email size must be 8 to 50")
    @Email( message = "must be email")
    private String email;

    @NotBlank(message = "password not blank")
    @Size(min = 4,max = 100,  message = "password size must be 4 to 100")
    private String password;

    @NotBlank(message = "firstname not blank")
    @Size(min = 2,max = 50, message = "firstname size must be 2 to 50")
    private String firstname;

    @NotBlank(message = "lastname not blank")
    @Size(min = 2,max = 50 , message = "lastname size must be 2 to 50")
    private String lastname;

    @Size(min = 9,max = 10 , message = "size not comfirm")
    private String phone;

    private String idrap;

    @NotNull(message = "role not null")
    private Set<String> role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getIdrap() { return idrap; }

    public void setIdrap(String idrap) { this.idrap = idrap; }

    public Set<String>  getRole() { return role; }

    public void setRole(Set<String>  role) {
        this.role = role;
    }
}
