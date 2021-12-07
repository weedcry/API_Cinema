package com.QCINE.Main.Entity;


import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "customer")
public class Customer_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCustomer;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(length = 50)
    private String phoneNumber;

    @Column(length = 500)
    private String avatar;

    private int promoPoint;

    @OneToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User_Entity userEntity;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.LAZY)
    private Collection<HoaDon_Entity> hoaDonEntity;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Collection<Comment_Entity> comment;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPromoPoint() {
        return promoPoint;
    }

    public void setPromoPoint(int promoPoint) {
        this.promoPoint = promoPoint;
    }

    public User_Entity getUserEntity() { return userEntity; }

    public void setUserEntity(User_Entity userEntity) { this.userEntity = userEntity; }

}
