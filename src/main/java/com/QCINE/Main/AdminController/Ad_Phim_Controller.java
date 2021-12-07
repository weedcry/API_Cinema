package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Filter.Phim_Filtered;
import com.QCINE.Main.DTO.LoaiPhim_DTO;
import com.QCINE.Main.DTO.Phim_DTO;
import com.QCINE.Main.Entity.Phim_Entity;
import com.QCINE.Main.Service.Phim_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/phim")
public class Ad_Phim_Controller {
    @Autowired
    Phim_Service phim_service;

    @GetMapping("")
    public ResponseEntity<Object> getAllPhim() {
        try {
            List<Phim_DTO> result = (List<Phim_DTO>) phim_service.getAllPhim();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Phim_Controller - getAllPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<Object> getSortedPhim(@RequestParam(name = "name", defaultValue = "") String name,
                                                @RequestParam(name = "pagenum", defaultValue = "0") int page,
                                                @RequestParam(name = "size", defaultValue = "10") int size,
                                                @RequestParam(name = "orderby", defaultValue = "ngayKC") String orderBy,
                                                @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                @RequestParam(name = "state", required = false)Optional<String> state) {
        try {
            Phim_Filtered result = (Phim_Filtered) phim_service.getPhimFiltered(name, page, size, orderBy, direction, state);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Phim_Controller - getSortedPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/state/{state}")
    public ResponseEntity getAllPhim_followedByState(@PathVariable("state") String state){
        try{
            List<Phim_DTO> result = (List<Phim_DTO>) phim_service.getAllPhim_followedByState(state);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in Phim_Controller - getAllPhim_followedByState: "+ e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPhim(@PathVariable("id") int idPhim) {
        try {
            Phim_DTO result = (Phim_DTO) phim_service.getPhim(idPhim);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Phim_Controller - getPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity<Object> updatePhim(@RequestBody Phim_DTO phim_dto) {
        try {
            if (phim_service.updatePhim(phim_dto))
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Phim_Controller - updatePhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> createPhim(@RequestBody Phim_DTO phim_dto) {
        try {
            if (phim_service.createPhim(phim_dto))
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Phim_Controller - createPhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePhim(@PathVariable("id") int id) {
        try {
            if (phim_service.deletePhim(id))
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Phim_Controller - deletePhim: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}