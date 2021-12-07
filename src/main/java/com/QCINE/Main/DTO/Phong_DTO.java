package com.QCINE.Main.DTO;

public class Phong_DTO {
    private String idPhong;
    private String tenPhong;
    private int soHang;
    private int soCot;
    private String tinhTrang;
    private String rapEntityIdRap;

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public int getSoHang() {
        return soHang;
    }

    public void setSoHang(int soHang) {
        this.soHang = soHang;
    }

    public int getSoCot() {
        return soCot;
    }

    public void setSoCot(int soCot) {
        this.soCot = soCot;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getRapEntityIdRap() {
        return rapEntityIdRap;
    }

    public void setRapEntityIdRap(String rapEntityIdRap) {
        this.rapEntityIdRap = rapEntityIdRap;
    }
}
