package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.KhuVuc_DTO;
import com.QCINE.Main.Service.KhuVuc_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/khuvuc")
public class Khuvuc_Controller {
    @Autowired
    KhuVuc_Service khuVuc_service;

    @GetMapping("/get")
    public ResponseEntity getAllKhuvuc(){
        List<KhuVuc_DTO> listkhuvuc = khuVuc_service.getAllKhuvuc();
        if(listkhuvuc.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listkhuvuc);
    }

}

