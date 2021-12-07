package com.QCINE.Main.DTO;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.List;

public class infoDetailLich_DTO {

    @NotNull
    private int idLich;

    @NotBlank
    private  String idPhong;

    @NotBlank
    private String tenPhong;

    @NotNull
    private int tongGhe;

    private List<infoVitri_DTO> listVitriSD;

    public int getIdLich() {
        return idLich;
    }

    public void setIdLich(int idLich) {
        this.idLich = idLich;
    }

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

    public int getTongGhe() {
        return tongGhe;
    }

    public void setTongGhe(int tongGhe) {
        this.tongGhe = tongGhe;
    }

    public List<infoVitri_DTO> getListVitriSD() {
        return listVitriSD;
    }

    public void setListVitriSD(List<infoVitri_DTO> listVitriSD) {
        this.listVitriSD = listVitriSD;
    }
}
