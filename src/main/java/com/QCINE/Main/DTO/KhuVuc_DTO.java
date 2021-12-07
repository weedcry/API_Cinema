package com.QCINE.Main.DTO;

import com.QCINE.Main.Entity.Rap_Entity;

import java.util.Collection;
import java.util.List;

public class KhuVuc_DTO {
    private String idKhuVuc;
    private String tenKhuVuc;
    private String diaChi;

    private List<Rap_DTO> listRap;

    public String getIdKhuVuc() {
        return idKhuVuc;
    }

    public void setIdKhuVuc(String idKhuVuc) {
        this.idKhuVuc = idKhuVuc;
    }

    public String getTenKhuVuc() {
        return tenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public List<Rap_DTO> getListRap() { return listRap; }

    public void setListRap(List<Rap_DTO> listRap) { this.listRap = listRap; }
}
