package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Holidays_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface Holidays_Repository extends JpaRepository<Holidays_Entity, String> {
    List<Holidays_Entity> findAllByStartDateLessThanEqualAndEndDateIsGreaterThanEqual(Date start, Date end);
}