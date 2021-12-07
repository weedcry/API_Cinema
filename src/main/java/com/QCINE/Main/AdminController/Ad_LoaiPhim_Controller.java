package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.LoaiPhim_DTO;
import com.QCINE.Main.Service.Loaiphim_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/loaiphim")
public class Ad_LoaiPhim_Controller {
    @Autowired
    Loaiphim_Service loaiPhim_service;

    @GetMapping("")
    public ResponseEntity<Object> getAllLoaiPhim(){
        try {
            List<LoaiPhim_DTO> result = (List<LoaiPhim_DTO>) loaiPhim_service.findAll();
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Controller - getAllLoaiPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLoaiPhim(@PathVariable("id") String idLoai){
        try {
            LoaiPhim_DTO result = (LoaiPhim_DTO) loaiPhim_service.findLoaiPhim(idLoai);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Controller - getLoaiPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity<Object> updateLoaiPhim(@RequestBody LoaiPhim_DTO loaiPhim_dto){
        try {
            if (loaiPhim_service.updateLoaiPhim(loaiPhim_dto))
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Controller - updateLoaiPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createLoaiPhim(@RequestBody LoaiPhim_DTO loaiPhim_dto){
        try {
            if (loaiPhim_service.createLoaiPhim(loaiPhim_dto))
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Controller - createLoaiPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteLoaiPhim(@PathVariable("id") String idLoai){
        try {
            if (loaiPhim_service.deleteLoaiPhim(idLoai))
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Controller - deleteLoaiPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}