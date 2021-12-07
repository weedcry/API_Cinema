package com.QCINE.Main.DTO;

public class Customer_DTO {
    private int idCustomer;
    private String Email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String avatar;
    private int promoPoint;
    private int userEntityIdUser;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getEmail() { return Email; }

    public void setEmail(String email) { Email = email; }

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

    public int getUserEntityIdUser() {
        return userEntityIdUser;
    }

    public void setUserEntityIdUser(int userEntityIdUser) {
        this.userEntityIdUser = userEntityIdUser;
    }
}
