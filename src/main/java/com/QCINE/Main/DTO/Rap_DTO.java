package com.QCINE.Main.DTO;


public class Rap_DTO {
    private String idRap;
    private String tenRap;
    private String diaChi;
    private float longitude;
    private float latitude;
    private String anh;
    private  String khuvucIdKhuVuc;

    public String getIdRap() {
        return idRap;
    }

    public void setIdRap(String idRap) {
        this.idRap = idRap;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diChi) {
        this.diaChi = diChi;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }


    public String getKhuvucIdKhuVuc() {
        return khuvucIdKhuVuc;
    }

    public void setKhuvucIdKhuVuc(String khuvucIdKhuVuc) {
        this.khuvucIdKhuVuc = khuvucIdKhuVuc;
    }
}
