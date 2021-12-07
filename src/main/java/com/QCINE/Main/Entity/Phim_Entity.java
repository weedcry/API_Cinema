package com.QCINE.Main.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;



@Entity
@Table(name ="phim")
public class Phim_Entity {

    public enum eTrangThai{
        ngungChieu,
        dangChieu,
        sapChieu
    }

    public enum eDoTuoi{
        C0,
        C13,
        C16,
        C18
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPhim;

    @Column(length = 100)
    private String tenPhim;

    @Column(length = 500)
    private String moTa;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date ngayKC;

    @Column(length = 100)
    private String theLoai;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private eDoTuoi doTuoi;

    @Column(length = 100)
    private String daoDien;

    @Column(length = 100)
    private String dienVien;

    @Column(length = 50)
    private String phanLoai;

    @Column(length = 500)
    private String anh;

    @Column(length = 500)
    private String thumbnail;

    @Column(length = 500)
    private String trailer;

    private int thoiLuong;



    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private eTrangThai trangThai;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date createdAt;


    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date updatedAt;


    private String updatedBy;

    @OneToMany(mappedBy = "phimEntity", fetch = FetchType.LAZY)
    private Collection<Lich_Entity> lichEntities;

    @OneToMany(mappedBy = "phim", fetch = FetchType.LAZY)
    private Collection<Comment_Entity> comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLoaiPhim")
    private LoaiPhim_Entity loaiPhimEntity;

    public int getIdPhim() {
        return idPhim;
    }

    public void setIdPhim(int idPhim) {
        this.idPhim = idPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayKC() {
        return ngayKC;
    }

    public void setNgayKC(Date ngayKC) {
        this.ngayKC = ngayKC;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getDienVien() { return dienVien; }

    public void setDienVien(String dienVien) { this.dienVien = dienVien; }

    public String getPhanLoai() {
        return phanLoai;
    }

    public void setPhanLoai(String phanLoai) {
        this.phanLoai = phanLoai;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public eTrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(eTrangThai trangThai) {
        this.trangThai = trangThai;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }



    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Collection<Lich_Entity> getLichEntities() {
        return lichEntities;
    }

    public void setLichEntities(Collection<Lich_Entity> lichEntities) {
        this.lichEntities = lichEntities;
    }

    public eDoTuoi getDoTuoi() {
        return doTuoi;
    }

    public void setDoTuoi(eDoTuoi doTuoi) {
        this.doTuoi = doTuoi;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Collection<Comment_Entity> getComment() { return comment; }

    public void setComment(Collection<Comment_Entity> comment) { this.comment = comment; }

    public LoaiPhim_Entity getLoaiPhimEntity() {
        return loaiPhimEntity;
    }

    public void setLoaiPhimEntity(LoaiPhim_Entity loaiPhimEntity) {
        this.loaiPhimEntity = loaiPhimEntity;
    }
}
