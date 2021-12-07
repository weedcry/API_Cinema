package com.QCINE.Main.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(length = 100)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(length = 50)
    private String username;

    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String idRap;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role_Entity> roles = new HashSet<>();

    @OneToOne(mappedBy = "userEntity")
    private Customer_Entity customerEntity;

    public User_Entity() {
    }

    public User_Entity(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

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

    public String getIdRap() { return idRap; }

    public void setIdRap(String idRap) { this.idRap = idRap; }

    public Set<Role_Entity> getRoles() { return roles; }

    public void setRoles(Set<Role_Entity> roles) { this.roles = roles; }

    public Customer_Entity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(Customer_Entity customerEntity) {
        this.customerEntity = customerEntity;
    }
}
