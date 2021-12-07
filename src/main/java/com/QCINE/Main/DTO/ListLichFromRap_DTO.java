package com.QCINE.Main.DTO;

import java.util.List;

public class ListLichFromRap_DTO {
    private String idRap;
    private String tenRap;
    private String idKhuvuc;
    private List<Lich_DTO> lich;

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

    public String getIdKhuvuc() {
        return idKhuvuc;
    }

    public void setIdKhuvuc(String idKhuvuc) {
        this.idKhuvuc = idKhuvuc;
    }

    public List<Lich_DTO> getLich() {
        return lich;
    }

    public void setLich(List<Lich_DTO> lich) {
        this.lich = lich;
    }
}
