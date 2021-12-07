package com.QCINE.Main.DTO;

public class LoaiPhim_DTO {
    private String idLoaiPhim;

    private String tenLoai;

    private String moTa;

    private float giaTien;

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

    public float getGiaTien() { return giaTien; }

    public void setGiaTien(float giaTien) { this.giaTien = giaTien; }
}
