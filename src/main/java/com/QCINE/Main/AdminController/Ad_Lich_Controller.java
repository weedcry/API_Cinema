package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Filter.Lich_Filtered;
import com.QCINE.Main.DTO.Lich_DTO;
import com.QCINE.Main.Service.Lich_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/lich")
public class Ad_Lich_Controller {
    @Autowired
    Lich_Service lich_service;

    @GetMapping("/filter")
    public ResponseEntity getFilteredLich(@RequestParam(name = "pagenum", defaultValue = "0") int pageNum,
                                          @RequestParam(name = "size", defaultValue = "10") int size,
                                          @RequestParam(name = "rap") String idRap,
                                          @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                          @RequestParam(name = "orderby", defaultValue = "ngay") String orderBy,
                                          @RequestParam(name = "date", required = false) Optional<Date> date,
                                          @RequestParam(name = "time", required = false) Optional<Time> time) {
        try {
            Lich_Filtered result = (Lich_Filtered) lich_service.getAllLichFiltered(pageNum, size, idRap, date, time, direction, orderBy);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Lich_Controller - getFilteredLich: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getLich(@PathVariable("id") int idLich) {
        try {
            Lich_DTO result = (Lich_DTO) lich_service.getLich(idLich);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Lich_Controller - getLich: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity createLich(@RequestBody Lich_DTO lich_dto) {
        try {
            if (lich_service.createLich(lich_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Lich_Controller - createLich: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/rap/{id}")
    public ResponseEntity getLichFollowedBy_Ngay_Phim_Rap(@PathVariable("id") String idRap,
                                                          @RequestBody Lich_DTO lich_dto){
        try{
            System.out.println("Received: Ngay: "+lich_dto.getNgay());
            System.out.println("Received: Phim: "+lich_dto.getPhimEntityIdPhim());
            System.out.println("Received: Rap: "+idRap);
            System.out.println("------------------------------------");
            List<Lich_DTO> result = lich_service.getLichFilteredBy_Ngay_Phim_Rap(idRap, lich_dto);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in Lich_Controller - getLichFollowedBy_Ngay_Phim_Rap: "+ e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity updateLich(@RequestBody Lich_DTO lich_dto) {
        try {
            if (lich_service.updateLich(lich_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Lich_Controller - updateLich: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLich(@PathVariable("id") int idLich) {
        try {
            if (lich_service.deleteLich(idLich))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Lich_Controller - deleteLich: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}