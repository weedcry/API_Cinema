package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.LoaiPhim_DTO;
import com.QCINE.Main.DTO.Role_DTO;
import com.QCINE.Main.Service.Role_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/role")
public class Ad_Role_Controller {
    @Autowired
    Role_Service role_service;

    @GetMapping("")
    public ResponseEntity getAllRole(){
        try {
            List<Role_DTO> result = (List<Role_DTO>) role_service.getAllRole();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Role_Controller - getAllRole: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getRole(@PathVariable("id") int id){
        try {
            Role_DTO result = (Role_DTO) role_service.getRole(id);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Role_Controller - getRole: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity createRole(@RequestBody Role_DTO role_dto){
        try {
            if (role_service.createRole(role_dto))
                return new ResponseEntity("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Role_Controller - createRole: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity updateRole(@RequestBody Role_DTO role_dto){
        try {
            if (role_service.updateRole(role_dto))
                return new ResponseEntity("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Role_Controller - updateRole: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRole(@PathVariable("id") int id){
        try {
            if (role_service.deleteRole(id))
                return new ResponseEntity("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Role_Controller - deleteRole: " + e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}