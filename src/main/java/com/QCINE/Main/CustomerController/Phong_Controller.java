package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.Phong_DTO;
import com.QCINE.Main.Service.Phong_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/phong")
public class Phong_Controller {

    @Autowired
    Phong_Service phong_service;

    @GetMapping("/{id}")
    public ResponseEntity getPhongById(@PathVariable String id){
        Phong_DTO phong_dto = phong_service.getPhongByIdphong(id);
        if(phong_dto == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(phong_dto);
    }

    @GetMapping("/rap/{id}")
    public ResponseEntity getPhongByIdRap(@PathVariable String id){
        List<Phong_DTO> listphong_dto = phong_service.getListPhongDTOFromRap(id);
        if(listphong_dto.size() == 0){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(listphong_dto);
    }

}
