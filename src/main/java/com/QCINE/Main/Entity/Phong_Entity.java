package com.QCINE.Main.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.Collection;


@Entity
@Table(name = "phong")
public class Phong_Entity {

    public enum eTinhTrang{
        baoTri,
        hoatDong
    }

    @Id
    @Column(length = 100)
    private String idPhong;

    @Column(length = 50)
    private String tenPhong;

    @Range(min = 8, max = 16)
    private int soHang;

    @Range(min = 14, max = 20)
    private int soCot;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private eTinhTrang tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idRap")
    private Rap_Entity rapEntity;

    @OneToMany(mappedBy = "phongEntity", fetch = FetchType.LAZY)
    private Collection<Lich_Entity> lichEntities;

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

    public int getSoHang() {
        return soHang;
    }

    public void setSoHang(int soHang) {
        this.soHang = soHang;
    }

    public int getSoCot() {
        return soCot;
    }

    public void setSoCot(int soCot) {
        this.soCot = soCot;
    }

    public eTinhTrang getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(eTinhTrang tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Rap_Entity getRapEntity() {
        return rapEntity;
    }

    public void setRapEntity(Rap_Entity rapEntity) {
        this.rapEntity = rapEntity;
    }

    public Collection<Lich_Entity> getLichEntities() {
        return lichEntities;
    }

    public void setLichEntities(Collection<Lich_Entity> lichEntities) {
        this.lichEntities = lichEntities;
    }
}
