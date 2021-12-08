package com.QCINE.Main.CustomerController;

import com.QCINE.Main.Entity.Holidays_Entity;
import com.QCINE.Main.Repository.Holidays_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/customer/holiday")
public class Holidays_Controller {

    @Autowired
    Holidays_Repository holidays_repository;

    @GetMapping("/get")
    public ResponseEntity getLoaiphim(){
        List<Holidays_Entity> list = holidays_repository.findAll();
        if(list.size() == 0){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
