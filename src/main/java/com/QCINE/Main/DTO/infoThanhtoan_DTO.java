package com.QCINE.Main.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class infoThanhtoan_DTO {
    private int idUser;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date createdAt;

    private int usedPoint;
    private String idVoucher;
    private String  hinhThuc;
    private String  motaThanhtoan;
    private int idlich;
    private List<CtHoaDon_DTO> listCTHoadon;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getUsedPoint() {
        return usedPoint;
    }

    public void setUsedPoint(int usedPoint) {
        this.usedPoint = usedPoint;
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public String getMotaThanhtoan() {
        return motaThanhtoan;
    }

    public void setMotaThanhtoan(String motaThanhtoan) {
        this.motaThanhtoan = motaThanhtoan;
    }

    public int getIdlich() {
        return idlich;
    }

    public void setIdlich(int idlich) {
        this.idlich = idlich;
    }

    public List<CtHoaDon_DTO> getListCTHoadon() {
        return listCTHoadon;
    }

    public void setListCTHoadon(List<CtHoaDon_DTO> listCTHoadon) {
        this.listCTHoadon = listCTHoadon;
    }
}
