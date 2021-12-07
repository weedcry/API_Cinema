package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Lich_DTO;
import com.QCINE.Main.DTO.Phong_DTO;
import com.QCINE.Main.Service.Phong_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/phong")
public class Ad_Phong_Controller {

    @Autowired
    Phong_Service phong_service;

    @GetMapping("")
    public ResponseEntity<Object> getAllPhong() {
        try {
            List<Phong_DTO> result = (List<Phong_DTO>) phong_service.getAllPhong();
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Phong_Controller - getAllPhong: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/rap/{id}")
    public ResponseEntity<Object> getAllPhongByRap(@PathVariable("id") String idRap) {
        try {
            List<Phong_DTO> result = (List<Phong_DTO>) phong_service.getAllPhongByRap(idRap);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Phong_Controller - getAllPhong: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity getAllPhongByStateAndRap(@RequestParam(name = "rap") String idRap,
                                                   @RequestParam(name = "state") String state) {
        try {
            List<Phong_DTO> result = (List<Phong_DTO>) phong_service.getAllPhongByRapAndState(idRap, state);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Phong_Controller - getAllPhongByStateAndRap: " + e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/usable")
    public ResponseEntity getUsablePhong(@RequestParam(name = "rap") String idRap,
                                         @RequestBody Lich_DTO lich_dto) {
        try {
            System.out.println("Lich - Ngay: " + lich_dto.getNgay());
            System.out.println("Lich - Gio: " + lich_dto.getGio());
            System.out.println("Lich - Phim: " + lich_dto.getPhimEntityIdPhim());
            List<Phong_DTO> result = (List<Phong_DTO>) phong_service.getUsablePhong(idRap, lich_dto);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPhong(@PathVariable("id") String idPhong) {
        try {
            Phong_DTO result = (Phong_DTO) phong_service.getPhong(idPhong);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Phong_Controller - getPhong: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createPhong(@RequestBody Phong_DTO phong_dto) {
        System.out.println("Received idRap:" + phong_dto.getRapEntityIdRap());
        if (phong_service.createPhong(phong_dto))
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else return new ResponseEntity<>("Failed", HttpStatus.NO_CONTENT);
    }

    @PutMapping("")
    public ResponseEntity<Object> updatePhong(@RequestBody Phong_DTO phong_dto) {
        if (phong_service.updatePhong(phong_dto))
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else return new ResponseEntity<>("Failed", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePhong(@PathVariable("id") String idPhong) {
        if (phong_service.deletePhong(idPhong))
            return new ResponseEntity<>("OK", HttpStatus.OK);
        else return new ResponseEntity<>("Failed", HttpStatus.NO_CONTENT);
    }
}