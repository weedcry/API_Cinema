package com.QCINE.Main.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;


@Entity
@Table(name = "voucher")
public class Voucher_Entity {
    public enum eTinhTrang{
        conHieuLuc,
        hetHieuLuc
    }

    @Id
    @Column(length = 100)
    private String idVoucher;

    @Column(length = 100)
    private String tenVoucher;

    @Column(length = 500)
    private String anh;

    @Column(length = 500)
    private String mota;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private eTinhTrang tinhTrang;

    private int percentageOff;

    private int soLanSD;

    @OneToMany(mappedBy = "voucherEntity", fetch =FetchType.LAZY )
    private Collection<HoaDon_Entity> hoaDonEntity;

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public eTinhTrang getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(eTinhTrang tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public int getPercentageOff() {
        return percentageOff;
    }

    public void setPercentageOff(int percentageOff) {
        this.percentageOff = percentageOff;
    }

    public int getSoLanSD() { return soLanSD; }

    public void setSoLanSD(int soLanSD) { this.soLanSD = soLanSD; }

    public Collection<HoaDon_Entity> getHoaDonEntity() {
        return hoaDonEntity;
    }

    public void setHoaDonEntity(Collection<HoaDon_Entity> hoaDonEntity) {
        this.hoaDonEntity = hoaDonEntity;
    }
}
