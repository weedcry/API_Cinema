package com.QCINE.Main.Entity;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Table(name = "cthoadon")
public class CtHoaDon_Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCT;

    private int hang;

    private int cot;

    private float gia;

    @ManyToOne
    @JoinColumn(name = "idHoaDon")
    private HoaDon_Entity hoaDonEntity;

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

    public HoaDon_Entity getHoaDonEntity() {
        return hoaDonEntity;
    }

    public void setHoaDonEntity(HoaDon_Entity hoaDonEntity) {
        this.hoaDonEntity = hoaDonEntity;
    }
}
