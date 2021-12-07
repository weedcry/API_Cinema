package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.KhuVuc_Entity;
import com.QCINE.Main.Entity.Rap_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface Rap_Repository extends JpaRepository<Rap_Entity, String> {

    List<Rap_Entity> findAllByKhuvucAndTinhTrang(KhuVuc_Entity khuvuc, Rap_Entity.eTinhTrang tinhTrang);

    Rap_Entity findByIdRap(String idRap);

    @Query(value="select * from rap where id_rap in (select id_rap from phong where id_phong in " +
            "(select id_phong from lich where id_phim = ?2 and ngay = ?1));",nativeQuery = true)
    List<Rap_Entity> findByDateAndIdPhim(Date date, int idphim);

//    Admin

    List<Rap_Entity> findAllByKhuvuc(KhuVuc_Entity khuVuc);
}

