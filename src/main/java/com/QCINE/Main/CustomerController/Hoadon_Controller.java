package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.Filter.HoaDon_Filtered;
import com.QCINE.Main.DTO.HoaDon_DTO;
import com.QCINE.Main.DTO.infoThanhtoan_DTO;
import com.QCINE.Main.Service.Hoadon_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/hoadon")
public class Hoadon_Controller {

    @Autowired
    Hoadon_Service hoadon_service;

    @GetMapping("/get")
    public ResponseEntity getHoadonFromCustomer(){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        List<HoaDon_DTO> listHoadon = hoadon_service.getHoadonCustomer(username);
        if(listHoadon.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listHoadon);
    }

    @PostMapping("/create")
    public ResponseEntity CreateHoadon(@Valid @RequestBody infoThanhtoan_DTO infoThanhtoan){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        Boolean check = hoadon_service.CreateHoadon(infoThanhtoan,username);
        System.out.println(check);
        if(!check){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity CancelHoadon(@PathVariable int id){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        Boolean check = hoadon_service.cancelHoadon(id,username);
        if(!check){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

}
