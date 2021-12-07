package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Rap_DTO;
import com.QCINE.Main.Service.Rap_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/rap")
public class Ad_Rap_Controller {
    @Autowired
    Rap_Service rap_service;

    @GetMapping("")
    public ResponseEntity<Object> getAllRap() {
        try {
            List<Rap_DTO> result = (List<Rap_DTO>) rap_service.getAll();
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Rap_Controller - getAllRap: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/khuvuc/{id}")
    public ResponseEntity<Object> getByKhuVuc(@PathVariable("id") String id){
        try {
            List<Rap_DTO> result = (List<Rap_DTO>) rap_service.getByKhuVuc(id);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Rap_Controller - getByKhuVuc: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRap(@PathVariable("id") String idRap) {
        try {
            Rap_DTO result = (Rap_DTO) rap_service.getById(idRap);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Rap_Controller - getRap: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createRap(@RequestBody Rap_DTO rap_dto) {
        try {
            if (rap_service.createRap(rap_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Rap_Controller - createRap: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity<Object> updateRap(@RequestBody Rap_DTO rap_dto){
        try {
            if (rap_service.updateRap(rap_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Rap_Controller - updateRap: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRap(@PathVariable("id") String id){
        try {
            if (rap_service.deleteRap(id))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Rap_Controller - deleteRap: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}