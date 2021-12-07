package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.Phim_DTO;
import com.QCINE.Main.DTO.ResultPage;
import com.QCINE.Main.Service.Phim_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/phim")
public class Phim_Controller {

    @Autowired
    Phim_Service phim_service;

    @GetMapping("/page")
    public ResponseEntity getListPhimPagination(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "8") int size,
            @RequestParam(name = "status", defaultValue = "dangchieu") String status,
            @RequestParam(name = "time", defaultValue = "0") String time,
            @RequestParam(name = "rap", defaultValue = "0") String  idrap){
        ResultPage resultPage = phim_service.getListPhimPagination(page,size,status.toUpperCase(),time,idrap);
        if(resultPage.getListResult().size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(resultPage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
    }

    @GetMapping("/{id}")
    public ResponseEntity getPhongById(@PathVariable int id){
        Phim_DTO phim_dto = phim_service.getPhimByIdphim(id);
        if(phim_dto.getIdPhim() == 0){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(phim_dto);
    }


}
