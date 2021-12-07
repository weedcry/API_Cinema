package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Filter.Customer_Filtered;
import com.QCINE.Main.DTO.thongke.OutputKVThongKe_DTO;
import com.QCINE.Main.DTO.thongke.OutputThongKe_DTO;
import com.QCINE.Main.Service.Thongke_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/thongke")
public class Ad_Thongke_Controller {
    @Autowired
    Thongke_Service thongke_service;

    @GetMapping("/rap")
    public ResponseEntity getThongkeRapbyDate(@RequestParam(name = "mode", defaultValue = "") String mode,
                                            @RequestParam(name = "idKhuvuc", defaultValue = "") String idKV,
                                            @RequestParam(name="idRap", defaultValue = "") String idRap,
                                            @RequestParam(name="idPhim", defaultValue = "") int idPhim,
                                            @RequestParam(name="dateFrom", defaultValue = "") String dateFrom,
                                            @RequestParam(name="dateTo", defaultValue = "") String dateTo){
        try{
            List<OutputThongKe_DTO> list = thongke_service.ThongkeRapByDate(mode,idKV,idRap,idPhim,dateFrom,dateTo);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("failed");
        }
    }

    @GetMapping("/khuvuc")
    public ResponseEntity getThongkeKVbyDate(@RequestParam(name = "mode", defaultValue = "") String mode,
                                              @RequestParam(name = "idKhuvuc", defaultValue = "") String idKV,
                                              @RequestParam(name="idRap", defaultValue = "") String idRap,
                                              @RequestParam(name="year", defaultValue = "") String year){
        try{
            Object list = thongke_service.ThongkeKVByDate(mode,idKV,idRap,year);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("failed");
        }
    }

}
