package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Role_Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Role_Repository extends JpaRepository<Role_Entity, Integer> {
    Optional<Role_Entity> findByRoleName(Role_Entity.role_name name);
}
