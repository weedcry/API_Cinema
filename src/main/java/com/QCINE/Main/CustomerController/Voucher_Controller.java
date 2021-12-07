package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.Voucher_DTO;
import com.QCINE.Main.Service.Voucher_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/voucher")
public class Voucher_Controller {

    @Autowired
    Voucher_Service voucher_service;

    @GetMapping("/get")
    public ResponseEntity getVoucherHieuluc(){
        List<Voucher_DTO> listVoucher = voucher_service.getAllVoucherHieuluc();
        if(listVoucher.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listVoucher);
    }

    @PostMapping("/check")
    public ResponseEntity checkVoucherUsed( @RequestParam(name = "idVoucher") Optional<String> idVoucher){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }
        Voucher_DTO check = voucher_service.CheckVoucherUsed(username,idVoucher.get());
        System.out.println(!ObjectUtils.isEmpty(check));
        if(check.getIdVoucher() != null){
            return ResponseEntity.status(HttpStatus.OK).body(check);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
    }
}
