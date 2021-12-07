package com.QCINE.Main.Repository;

import com.QCINE.Main.Entity.Voucher_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Voucher_Repository extends JpaRepository<Voucher_Entity, String> {

    List<Voucher_Entity> findByTinhTrang(Voucher_Entity.eTinhTrang tinhTrang);

    Voucher_Entity findByIdVoucher(String idVoucher);

//    Admim

    List<Voucher_Entity> findAllByTinhTrang(Voucher_Entity.eTinhTrang tinhTrang);
}
