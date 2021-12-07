package com.QCINE.Main.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hoadon")
public class HoaDon_Entity {

    public enum eTinhTrang{
        thanhCong,
        Huy
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHoaDon;

    private int idUser;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
    private Date createdAt;

    private float total;

    private int usedPoint;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private  eTinhTrang tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCustomer")
    private Customer_Entity customerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idVoucher")
    private Voucher_Entity voucherEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idThanhtoan", referencedColumnName = "idThanhToan")
    private ThanhToan_Entity thanhToanEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLich")
    private Lich_Entity lichEntity;

    @OneToMany(mappedBy = "hoaDonEntity",cascade = CascadeType.ALL)
    private Collection<CtHoaDon_Entity> CtHoaDon_Entity ;

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

    public Customer_Entity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(Customer_Entity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public Voucher_Entity getVoucherEntity() {
        return voucherEntity;
    }

    public void setVoucherEntity(Voucher_Entity voucherEntity) {
        this.voucherEntity = voucherEntity;
    }

    public ThanhToan_Entity getThanhToanEntity() {
        return thanhToanEntity;
    }

    public void setThanhToanEntity(ThanhToan_Entity thanhToanEntity) {
        this.thanhToanEntity = thanhToanEntity;
    }

    public Lich_Entity getLichEntity() {
        return lichEntity;
    }

    public void setLichEntity(Lich_Entity lichEntity) {
        this.lichEntity = lichEntity;
    }

    public eTinhTrang getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(eTinhTrang tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Collection<com.QCINE.Main.Entity.CtHoaDon_Entity> getCtHoaDon_Entity() {
        return CtHoaDon_Entity;
    }

    public void setCtHoaDon_Entity(Collection<com.QCINE.Main.Entity.CtHoaDon_Entity> ctHoaDon_Entity) {
        CtHoaDon_Entity = ctHoaDon_Entity;
    }
}
