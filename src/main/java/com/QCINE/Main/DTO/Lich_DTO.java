package com.QCINE.Main.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.util.Date;

public class Lich_DTO {
    private int idLich;
    private Date ngay;
    private Time gio;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date createdAt;
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date updatedAt;
    private String updatedBy;
    private float gia;
    private String phongEntityIdPhong;
    private int phimEntityIdPhim;

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

    public String getPhongEntityIdPhong() {
        return phongEntityIdPhong;
    }

    public void setPhongEntityIdPhong(String phongEntityIdPhong) {
        this.phongEntityIdPhong = phongEntityIdPhong;
    }

    public int getPhimEntityIdPhim() {
        return phimEntityIdPhim;
    }

    public void setPhimEntityIdPhim(int phimEntityIdPhim) {
        this.phimEntityIdPhim = phimEntityIdPhim;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
}
