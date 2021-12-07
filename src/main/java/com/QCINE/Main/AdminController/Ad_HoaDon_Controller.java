package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Filter.HoaDon_Filtered;
import com.QCINE.Main.DTO.HoaDon_DTO;
import com.QCINE.Main.Service.Hoadon_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("admin/hoadon")
public class Ad_HoaDon_Controller {
    @Autowired
    Hoadon_Service hoaDon_service;

    @GetMapping("/filter")
    public ResponseEntity getHoaDonFiltered(@RequestParam(name = "pagenum", defaultValue = "0") int pageNum,
                                            @RequestParam(name = "size", defaultValue = "10") int size,
                                            @RequestParam(name="buyer", required = false) Optional<Integer> buyEr,
                                            @RequestParam(name = "buyertype", defaultValue = "admin") String buyerType,
                                            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                            @RequestParam(name = "orderby", defaultValue = "createdAt") String orderBy){
        try{
            HoaDon_Filtered result = (HoaDon_Filtered) hoaDon_service.getHoaDonFiltered(pageNum, size, buyEr, buyerType, direction, orderBy);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in HoaDon_Controller - getHoaDonFiltered: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getHoaDon(@PathVariable("id") int idHoaDon){
        try{
            HoaDon_DTO result = (HoaDon_DTO) hoaDon_service.getHoaDon(idHoaDon);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in HoaDon_Controller - getHoaDon: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity createHoaDon(HoaDon_DTO hoaDon_dto){
        try{
            if (hoaDon_service.createHoaDon(hoaDon_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in HoaDon_Controller - createHoaDon: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity updateHoaDon(HoaDon_DTO hoaDon_dto){
        try{
            if (hoaDon_service.updateHoaDon(hoaDon_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in HoaDon_Controller - updateHoaDon: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("")
    public ResponseEntity deleteHoaDon(@PathVariable("id") int idHoaDon){
        try{
            if (hoaDon_service.deleteHoaDon(idHoaDon))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in HoaDon_Controller - deleteHoaDon: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}