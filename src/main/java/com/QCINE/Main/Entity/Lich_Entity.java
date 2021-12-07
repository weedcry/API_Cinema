package com.QCINE.Main.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "lich")
public class Lich_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLich;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngay;


    private Time gio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private String updatedBy;

    private float gia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPhong")
    private Phong_Entity phongEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPhim")
    private Phim_Entity phimEntity;

    @OneToMany(mappedBy = "lichEntity", fetch = FetchType.LAZY)
    private Collection<HoaDon_Entity> hoaDonEntity;

    public int getIdLich() {
        return idLich;
    }

    public void setIdLich(int idLich) {
        this.idLich = idLich;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public Time getGio() {
        return gio;
    }

    public void setGio(Time gio) {
        this.gio = gio;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Phong_Entity getPhongEntity() {
        return phongEntity;
    }

    public void setPhongEntity(Phong_Entity phongEntity) {
        this.phongEntity = phongEntity;
    }

    public Phim_Entity getPhimEntity() {
        return phimEntity;
    }

    public void setPhimEntity(Phim_Entity phimEntity) {
        this.phimEntity = phimEntity;
    }

    public Collection<HoaDon_Entity> getHoaDonEntity() {
        return hoaDonEntity;
    }

    public void setHoaDonEntity(Collection<HoaDon_Entity> hoaDonEntity) {
        this.hoaDonEntity = hoaDonEntity;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
}
