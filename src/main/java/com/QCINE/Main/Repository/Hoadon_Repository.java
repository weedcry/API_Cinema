package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Customer_Entity;
import com.QCINE.Main.Entity.HoaDon_Entity;
import com.QCINE.Main.Entity.Lich_Entity;
import com.QCINE.Main.Entity.Voucher_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Hoadon_Repository extends JpaRepository<HoaDon_Entity,Integer> {

    HoaDon_Entity findByIdHoaDon(int idHoaDon);

    List<HoaDon_Entity> findByCustomerEntity(Customer_Entity customer_Entity);

    List<HoaDon_Entity> findByLichEntityAndTinhTrang(Lich_Entity lichEntity, HoaDon_Entity.eTinhTrang tinhTrang);

    List<HoaDon_Entity> findByCustomerEntityAndVoucherEntity(Customer_Entity customerEntity, Voucher_Entity voucherEntity);

    List<HoaDon_Entity> findByLichEntity(Lich_Entity lichEntity);

//    Admin

    Page<HoaDon_Entity> findAllByCustomerEntity(Customer_Entity customer, Pageable page);

    Page<HoaDon_Entity> findAllByCustomerEntityIsNotNull(Pageable page);

    Page<HoaDon_Entity> findAllByIdUser(int idUser, Pageable page);

    Page<HoaDon_Entity> findAllByIdUserIsNotNull(Pageable page);

    @Query(value=" SELECT * FROM hoadon WHERE tinh_trang = 'thanhCong' AND  " +
            " id_lich in (SELECT id_lich FROM lich where ngay >= ?2 AND ngay <= ?3 AND" +
            "  id_phong in (SElECT id_phong FROM phong where id_rap = ?1 ))", nativeQuery = true)
    List<HoaDon_Entity> findByIdRapAndDate(String idRap, String dateFrom, String dateTo);


    @Query(value=" SELECT * FROM hoadon WHERE tinh_trang = 'thanhCong' AND  " +
            " id_lich in (SELECT id_lich FROM lich where ngay >= ?2 AND ngay <= ?3 AND" +
            "  id_phong in (SElECT id_phong FROM phong where id_rap in ( SELECT id_rap FROM rap WHERE id_khu_vuc = ?1 ) ))", nativeQuery = true)
    List<HoaDon_Entity> findByIdKhuvucAndDate(String idKhuvuc, String dateFrom, String dateTo);

    @Query(value=" SELECT * FROM hoadon WHERE tinh_trang = 'thanhCong' AND  " +
            " id_lich in (SELECT id_lich FROM lich where ngay >= ?3 AND ngay <= ?4 AND" +
            " id_phim = ?2 AND id_phong in (SElECT id_phong FROM phong where id_rap = ?1 ))", nativeQuery = true)
    List<HoaDon_Entity> findByIdRapAndPhimAndDate(String idRap,int idPhim, String dateFrom, String dateTo);

}
