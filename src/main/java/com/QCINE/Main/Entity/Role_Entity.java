package com.QCINE.Main.Entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role_Entity {

    public enum role_name {
        AUTHOR,ADMIN,STAFF,USER;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private role_name roleName;

    private String moTa;

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public role_name getRoleName() {
        return roleName;
    }

    public void setRoleName(role_name roleName) {
        this.roleName = roleName;
    }
}
