package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.KhuVuc_Entity;
import com.QCINE.Main.Entity.Lich_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhuVuc_Repository extends JpaRepository<KhuVuc_Entity, String> {
    KhuVuc_Entity findByIdKhuVuc(String idKhuVuc);

}
