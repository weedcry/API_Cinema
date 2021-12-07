package com.QCINE.Main.DTO;

public class CtHoaDon_DTO {
    private int idCT;
    private int hang;
    private int cot;
    private float gia;
    private int hoaDonEntityIdHoaDon;

    public int getIdCT() {
        return idCT;
    }

    public void setIdCT(int idCT) {
        this.idCT = idCT;
    }

    public int getHang() {
        return hang;
    }

    public void setHang(int hang) {
        this.hang = hang;
    }

    public int getCot() {
        return cot;
    }

    public void setCot(int cot) {
        this.cot = cot;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getHoaDonEntityIdHoaDon() {
        return hoaDonEntityIdHoaDon;
    }

    public void setHoaDonEntityIdHoaDon(int hoaDonEntityIdHoaDon) {
        this.hoaDonEntityIdHoaDon = hoaDonEntityIdHoaDon;
    }
}
