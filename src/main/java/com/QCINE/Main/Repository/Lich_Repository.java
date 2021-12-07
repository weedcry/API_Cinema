package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Lich_Entity;
import com.QCINE.Main.Entity.Phim_Entity;
import com.QCINE.Main.Entity.Phong_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface Lich_Repository extends JpaRepository<Lich_Entity, Integer> {

    Lich_Entity findByIdLich(int idLich);

    @Query(value="SELECT * FROM lich WHERE lich.ngay = ?1 and " +
            "lich.id_phong in (SELECT id_phong from phong where id_rap = ?2) ORDER BY lich.id_phim", nativeQuery = true)
    List<Lich_Entity> findByNgayAndIdrap(Date ngay, String idrap);

    @Query(value="SELECT * FROM lich WHERE lich.ngay = ?1 and id_phim= ?2 and " +
            "lich.id_phong in (SELECT id_phong from phong where id_rap = ?3)", nativeQuery = true)
    List<Lich_Entity> findByNgayAndIdphimAndIdrap(Date ngay, int idphim, String idrap);

    @Query(value="SELECT * FROM lich WHERE lich.ngay = ?1 and id_phim= ?2 and " +
            "lich.id_phong in (SELECT id_phong from phong where id_rap in " +
            " (SELECT id_rap from rap where id_khu_vuc = ?3))", nativeQuery = true)
    List<Lich_Entity> findByNgayAndIdphimAndIdKhuvuc(Date ngay, int idphim, String idkhuvuc);

    //Admin

    Page<Lich_Entity> getAllByNgayAfter(Date date, Pageable page);

    Page<Lich_Entity> getAllByPhongEntityRapEntityIdRap(String idRap, Pageable page);

    Page<Lich_Entity> getAllByNgayAndGioAfter(Date date, Time time, Pageable page);

    List<Lich_Entity> getAllByNgayAndPhongEntityRapEntityIdRap(Date ngayChieu, String idRap);

    List<Lich_Entity> getAllByNgayAndPhimEntity_IdPhimAndPhongEntity_RapEntity_IdRap(Date ngay, int idPhim, String idRap);

    @Query(value = "from Lich_Entity where ngay=?1 and phimEntity.idPhim=?2 and phongEntity.rapEntity.idRap=?3")
    List<Lich_Entity> getLichFilteredBy_ngay_phim_rap(Date ngay, int idPhim, String idRap);


}
