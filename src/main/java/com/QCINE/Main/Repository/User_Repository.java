package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Role_Entity;
import com.QCINE.Main.Entity.User_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface User_Repository extends JpaRepository<User_Entity, Integer> {

    Optional<User_Entity> findByUsername(String username);

    @Query(value = "select * from user where username = ?1",nativeQuery = true)
    User_Entity findUser(String username);

    Boolean existsByUsername(String username);

    User_Entity findByEmail(String email);

    Boolean existsByEmail(String email);

//    Admin

    Optional<User_Entity> findByUsernameAndPassword(String username, String password);

//    Page<User_Entity> findAllByRoleEntityAndNameContaining(Role_Entity role, String name, Pageable page);

    Page<User_Entity> findAllByNameContaining(String name, Pageable page);
}

