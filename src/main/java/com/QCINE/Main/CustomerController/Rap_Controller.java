package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.Rap_DTO;
import com.QCINE.Main.Service.Rap_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/rap")
public class Rap_Controller {

    @Autowired
    Rap_Service rap_service;

    @GetMapping("/{idRap}")
    public ResponseEntity getRapById(@PathVariable String idRap){
        Rap_DTO Rap = rap_service.getRapbyId(idRap);
        if(Rap.getIdRap() == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(Rap);
    }

    @GetMapping("/active")
    public ResponseEntity getPhongById(  @RequestParam(name = "phim", defaultValue = "0") int idphim,
                                         @RequestParam(name = "time", defaultValue = "0") String time){
        List<Rap_DTO> lichRap = rap_service.getAllRapByPhimAndTime(idphim,time);
        if(lichRap.size() == 0){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(lichRap);
    }

}
