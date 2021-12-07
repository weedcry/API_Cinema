package com.QCINE.Main.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

public class HoaDon_DTO {
    private int idHoaDon;
    private int idUser;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date createdAt;

    private float total;
    private int usedPoint;
    private String tinhTrang;
    private int customerEntityIdCustomer;
    private String voucherEntityIdVoucher;
    private ThanhToan_DTO thanhToan;
    private Lich_DTO lich;
    private List<CtHoaDon_DTO> listCTHoadon;


    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getUsedPoint() {
        return usedPoint;
    }

    public void setUsedPoint(int usedPoint) {
        this.usedPoint = usedPoint;
    }

    public String getTinhTrang() { return tinhTrang; }

    public void setTinhTrang(String tinhTrang) { this.tinhTrang = tinhTrang; }

    public int getCustomerEntityIdCustomer() {
        return customerEntityIdCustomer;
    }

    public void setCustomerEntityIdCustomer(int customerEntityIdCustomer) { this.customerEntityIdCustomer = customerEntityIdCustomer; }

    public String getVoucherEntityIdVoucher() {
        return voucherEntityIdVoucher;
    }

    public void setVoucherEntityIdVoucher(String voucherEntityIdVoucher) { this.voucherEntityIdVoucher = voucherEntityIdVoucher; }

    public ThanhToan_DTO getThanhToan() { return thanhToan; }

    public void setThanhToan(ThanhToan_DTO thanhToan) { this.thanhToan = thanhToan; }

    public Lich_DTO getLich() { return lich; }

    public void setLich(Lich_DTO lich) { this.lich = lich; }

    public List<CtHoaDon_DTO> getListCTHoadon() { return listCTHoadon; }

    public void setListCTHoadon(List<CtHoaDon_DTO> listCTHoadon) {
        this.listCTHoadon = listCTHoadon;
    }
}
