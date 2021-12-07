package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.CtHoaDon_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CtHoadon_Repository  extends JpaRepository<CtHoaDon_Entity, Integer> {

    int countCtHoaDon_EntitiesByHoaDonEntity_LichEntity_PhimEntity_IdPhim(int idPhim);

    List<CtHoaDon_Entity> getAllByHoaDonEntity_IdHoaDon(int idHoaDon);

    Boolean deleteAllByHoaDonEntity_IdHoaDon(int idHoaDon);
}
