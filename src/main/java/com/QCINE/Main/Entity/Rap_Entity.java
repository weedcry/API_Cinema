package com.QCINE.Main.Entity;

import com.QCINE.Main.DTO.Rap_DTO;
import com.QCINE.Main.Repository.Rap_Repository;
import com.QCINE.Main.Service.Rap_Service;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rap")
public class Rap_Entity {

    public enum eTinhTrang{
        baoTri,
        hoatDong
    }

    @Id
    @Column(length = 100)
    private String idRap;

    @Column(length = 100)
    private String tenRap;

    @Column(length = 1000)
    private String diaChi;


    private float longitude;


    private float latitude;

    @Column(length = 500)
    private String anh;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private eTinhTrang tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idKhuVuc")
    private KhuVuc_Entity khuvuc;

    @OneToMany(mappedBy = "rapEntity", fetch = FetchType.LAZY)
    private Collection<Phong_Entity> phongEntity;

    public Rap_Entity(Rap_DTO rap_dto) {
        this.anh = rap_dto.getAnh();
        this.diaChi = rap_dto.getDiaChi();
        this.idRap = rap_dto.getIdRap();
        this.latitude = rap_dto.getLatitude();
        this.longitude = rap_dto.getLongitude();
        this.tenRap = rap_dto.getTenRap();
    }

    public Rap_Entity() {
    }

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

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public KhuVuc_Entity getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(KhuVuc_Entity khuvuc) {
        this.khuvuc = khuvuc;
    }

    public Collection<Phong_Entity> getPhongEntity() {
        return phongEntity;
    }

    public void setPhongEntity(Collection<Phong_Entity> phongEntity) {
        this.phongEntity = phongEntity;
    }
}
