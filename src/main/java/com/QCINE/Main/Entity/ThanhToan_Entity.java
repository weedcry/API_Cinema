package com.QCINE.Main.Entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name = "thanhtoan")
public class ThanhToan_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idThanhToan;

    @Column(length = 10)
    private String hinhThuc;

    @Column(length = 500)
    private String moTa;

    @OneToOne(mappedBy = "thanhToanEntity")
    private HoaDon_Entity hoaDonEntity;

    public int getIdThanhToan() {
        return idThanhToan;
    }

    public void setIdThanhToan(int idThanhToan) {
        this.idThanhToan = idThanhToan;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}
