package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Phim_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface Phim_Repository extends JpaRepository<Phim_Entity, Integer> {

    Page<Phim_Entity> findByTrangThai( Phim_Entity.eTrangThai trangThai, Pageable pageable);

    Phim_Entity findByIdPhim(int idPhim);

    @Query(value="SELECT * FROM phim where id_phim in (SELECT lich.id_phim FROM lich WHERE lich.ngay = ?1 and " +
            "lich.id_phong in (SELECT id_phong from phong where id_rap = ?2))", nativeQuery = true)
    List<Phim_Entity> findPhimByNgayKCAndIdRap(Date ngayKC,String idRap);

//    Admin

    Page<Phim_Entity> findAllByTenPhimContaining(String name, Pageable page);

    List<Phim_Entity> findAllByTrangThai(Phim_Entity.eTrangThai state);

    Page<Phim_Entity> findAllByTrangThaiAndTenPhimContaining(Phim_Entity.eTrangThai state, String name, Pageable page);

    @Query(value="SELECT * FROM phim WHERE id_phim in (SELECT DISTINCT id_phim FROM lich WHERE ngay >= ?2 AND ngay <= ?3 " +
            "AND id_phong in (SElECT id_phong FROM phong WHERE id_rap = ?1 ))", nativeQuery = true)
    List<Phim_Entity> findByIdRapAndDate(String idRap,String dateFrom, String dateTo);

}
