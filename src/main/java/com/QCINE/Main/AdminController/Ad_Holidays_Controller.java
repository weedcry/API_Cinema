package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Filter.Holidays_Filtered;
import com.QCINE.Main.DTO.Holidays_DTO;
import com.QCINE.Main.Entity.Lich_Entity;
import com.QCINE.Main.Service.Holidays_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/holidays")
public class Ad_Holidays_Controller {
    @Autowired
    Holidays_Service holidays_service;

    @GetMapping("")
    public ResponseEntity getAllHolidays(@RequestParam(name = "page", defaultValue = "0") int pageNum,
                                         @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            Holidays_Filtered result = (Holidays_Filtered) holidays_service.getAllHolidays(pageNum, size);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Holidays_Controller - getAllHolidays: " + e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getHolidays(@PathVariable("id") String id) {
        try {
            Holidays_DTO holidays_dto = (Holidays_DTO) holidays_service.getHoliday(id);
            return new ResponseEntity(holidays_dto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("Error in Holidays_Controller - getHolidays: " + e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
    }

    @PostMapping("/price")
    public ResponseEntity getHolidayPrice(@RequestBody Lich_Entity lich_entity){
        float price = 0;
        try{
            price = holidays_service.getHolidayPrice(lich_entity.getNgay());
        } catch (Exception e){
            System.out.println("Error in Holidays_Controller - getHolidayPrice: "+ e.getStackTrace());
        }
        return new ResponseEntity(price, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity createHoliday(@RequestBody Holidays_DTO holidays_dto) {
        try {
            if (holidays_service.createHoliday(holidays_dto))
                return new ResponseEntity(HttpStatus.OK);
            else return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Holidays_Controller - createHolidays: " + e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity updateHoliday(@RequestBody Holidays_DTO holidays_dto) {
        try {
            if (holidays_service.updateHoliday(holidays_dto))
                return new ResponseEntity(HttpStatus.OK);
            else return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Holidays_Controller - deleteHoliday: " + e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHoliday(@PathVariable("id") String id) {
        try {
            if (holidays_service.deleteHoliday(id))
                return new ResponseEntity(HttpStatus.OK);
            else return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error in Holidays_Controller - deleteHoliday: " + e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}