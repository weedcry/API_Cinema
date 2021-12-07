package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.ChangePassword;
import com.QCINE.Main.DTO.Customer_DTO;
import com.QCINE.Main.DTO.Filter.Customer_Filtered;
import com.QCINE.Main.Service.Customer_Service;
import com.QCINE.Main.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class Customer_Controller {

    @Autowired
    Customer_Service customer_service;

    @GetMapping("/get")
    public ResponseEntity getCustomer(){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        Customer_DTO customer_dto = customer_service.findCustomerDTO(username);
        if(customer_dto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(customer_dto);
    }

    @PostMapping("/update")
    public ResponseEntity updateCustomer (@Valid @RequestBody Customer_DTO customer_dto){
        String username = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        if(!customer_service.updateCustomer(customer_dto, username)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failed");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @PostMapping("/password")
    public ResponseEntity<Object> ChangePassword(@RequestBody ChangePassword changePassword){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        }

        int check = customer_service.checkpassword(username,changePassword.getOldpassword());
        if(check == 0){
            MessageResponse mes = new MessageResponse("password incorrect");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mes);
        }
        return new ResponseEntity<Object>(customer_service.changepassword(username,changePassword.getNewpassword()),HttpStatus.OK);
    }

}
