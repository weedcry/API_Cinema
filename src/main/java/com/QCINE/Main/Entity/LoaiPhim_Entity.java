package com.QCINE.Main.Entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "loaiphim")
public class LoaiPhim_Entity {
    @Id
    @Column(length = 10)
    private String idLoaiPhim;

    @Column(length = 100)
    private String tenLoai;

    @Column(length = 500)
    private String moTa;

    @Column(length = 50)
    private Float giaTien;

    @OneToMany(mappedBy = "loaiPhimEntity", fetch = FetchType.LAZY)
    private Collection<Phim_Entity> phimEntities;

    public String getIdLoaiPhim() {
        return idLoaiPhim;
    }

    public void setIdLoaiPhim(String idLoaiPhim) {
        this.idLoaiPhim = idLoaiPhim;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Float getGiaTien() { return giaTien; }

    public void setGiaTien(Float giaTien) { this.giaTien = giaTien; }
}
