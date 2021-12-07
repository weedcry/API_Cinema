package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.ThanhToan_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThanhToan_Repository extends JpaRepository<ThanhToan_Entity, Integer> {

    ThanhToan_Entity findByMoTa(String moTa);

}
