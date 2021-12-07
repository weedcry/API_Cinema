package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Phong_Entity;
import com.QCINE.Main.Entity.Rap_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Phong_Repository extends JpaRepository<Phong_Entity, String> {
    List<Phong_Entity> getAllByRapEntity(Rap_Entity rap);

    Phong_Entity findByIdPhongAndTinhTrang(String idPhong, Phong_Entity.eTinhTrang tinhTrang);

    List<Phong_Entity> findByRapEntityAndTinhTrang(Rap_Entity rapEntity, Phong_Entity.eTinhTrang tinhTrang);

//    Admin

    List<Phong_Entity> getAllByRapEntityIdRapAndTinhTrang(String idRap, Phong_Entity.eTinhTrang state);
}

