package com.QCINE.Main.payload.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class LoginRequest {

    @NotBlank(message = "username not blank")
    @Size(min = 3,max = 50, message = " username size must be 3 to 50")
    @Email( message = "must be email")
    private String username;

    @NotBlank(message = "username not blank")
    @Size(min = 3,max = 50, message = " password size must be 3 to 50")
    private  String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
}
