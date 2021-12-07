package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.LoaiPhim_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Loaiphim_Repository extends JpaRepository<LoaiPhim_Entity,String> {


}
