package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.KhuVuc_DTO;
import com.QCINE.Main.Service.KhuVuc_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/khuvuc")
public class Ad_KhuVuc_Controller {
    @Autowired
    KhuVuc_Service khuVuc_service;

    @GetMapping("")
    public ResponseEntity<Object> getAllKhuVuc() {
        try {
            List<KhuVuc_DTO> result = (List<KhuVuc_DTO>) khuVuc_service.findAll();
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Controller - getAllKhuVuc: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getKhuVuc(@PathVariable("id") String id) {
        try {
            KhuVuc_DTO result = (KhuVuc_DTO) khuVuc_service.findById(id);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Controller - getKhuVuc: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity<Object> updateKhuVuc(@RequestBody KhuVuc_DTO khuVuc) {
        if (khuVuc_service.updateKhuVuc(khuVuc)) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity<Object> createKhuVuc(@RequestBody KhuVuc_DTO khuVuc_dto) {
        try {
            if (khuVuc_service.createKhuVuc(khuVuc_dto))
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Controller - createKhuVuc: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteKhuVuc(@PathVariable("id") String id) {
        try {
            if (khuVuc_service.deleteKhuVuc(id))
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Controller - deleteKhuVuc: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}