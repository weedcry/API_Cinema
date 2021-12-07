package com.QCINE.Main.DTO;

import javax.persistence.Column;
import java.util.List;

public class ListLichFromPhim_DTO {

    private int idPhim;
    private String tenPhim;
    private String phanLoai;
    private String doTuoi;
    private String LoaiPhim;
    private int thoiLuong;
    private List<Lich_DTO> lich;

    public int getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(int idPhim) {
        this.idPhim = idPhim;
    }

    public String getTenPhim() { return tenPhim; }

    public String getPhanLoai() { return phanLoai; }

    public void setPhanLoai(String phanLoai) { this.phanLoai = phanLoai; }

    public String getDoTuoi() { return doTuoi; }

    public void setDoTuoi(String doTuoi) { this.doTuoi = doTuoi; }

    public String getLoaiPhim() { return LoaiPhim; }

    public void setLoaiPhim(String loaiPhim) { LoaiPhim = loaiPhim; }

    public void setTenPhim(String tenPhim) { this.tenPhim = tenPhim; }

    public int getThoiLuong() { return thoiLuong; }

    public void setThoiLuong(int thoiLuong) { this.thoiLuong = thoiLuong; }

    public List<Lich_DTO> getLich() {
        return lich;
    }

    public void setLich(List<Lich_DTO> lich) {
        this.lich = lich;
    }
}
