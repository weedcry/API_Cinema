package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.LoaiPhim_DTO;
import com.QCINE.Main.Service.Loaiphim_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/customer/loaiphim")
public class Loaiphim_Controller {

    @Autowired
    Loaiphim_Service loaiphim_service;

    @GetMapping("/get")
    public ResponseEntity getLoaiphim(){
        List<LoaiPhim_DTO> listloaiphimDTO = loaiphim_service.getListLoaiPhim();
        if(listloaiphimDTO.size() == 0){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(listloaiphimDTO);
    }


}
