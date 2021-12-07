package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Week_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Week_Repository extends JpaRepository<Week_Entity, String> {

}