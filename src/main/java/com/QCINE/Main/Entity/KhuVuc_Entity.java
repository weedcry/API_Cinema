package com.QCINE.Main.Entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "khuvuc")
public class KhuVuc_Entity {

    @Id
    @Column(length = 100)
    private String idKhuVuc;

    @Column(length = 50)
    private String tenKhuVuc;

    @Column(length = 500)
    private String diaChi;

    @OneToMany(mappedBy = "khuvuc", fetch = FetchType.LAZY)
    private Collection<Rap_Entity> rapEntity;

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

    public Collection<Rap_Entity> getRapEntity() {
        return rapEntity;
    }

    public void setRapEntity(Collection<Rap_Entity> rapEntity) {
        this.rapEntity = rapEntity;
    }
}
