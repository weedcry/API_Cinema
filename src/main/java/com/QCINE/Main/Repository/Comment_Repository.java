package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Comment_Entity;
import com.QCINE.Main.Entity.Customer_Entity;
import com.QCINE.Main.Entity.Embedded.CommentId;
import com.QCINE.Main.Entity.Phim_Entity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Comment_Repository extends JpaRepository<Comment_Entity, CommentId> {

    Comment_Entity findByPhimAndCustomer(Phim_Entity phim, Customer_Entity customer);

    Page<Comment_Entity> findByPhim(Phim_Entity phim, Pageable pageable);
}
