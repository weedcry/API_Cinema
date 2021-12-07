package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Customer_Entity;
import com.QCINE.Main.Entity.User_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Customer_Repository  extends JpaRepository<Customer_Entity,Integer> {

    Customer_Entity findByUserEntity(User_Entity user_Entity);

    Customer_Entity findByIdCustomer(int idCustomer);

//    Admin

    Page<Customer_Entity> findAll(Pageable page);
}
