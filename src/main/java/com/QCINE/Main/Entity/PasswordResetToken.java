package com.QCINE.Main.Entity;
import javax.persistence.*;
import java.util.Date;

@Entity
public class PasswordResetToken {
//    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String token;

    private String code;

    @OneToOne(targetEntity = User_Entity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "idUser")
    private User_Entity user;

    private long expiryDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User_Entity getUser() {
        return user;
    }

    public void setUser(User_Entity user) {
        this.user = user;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(long expiryDate) {
        this.expiryDate = expiryDate;
    }
}
