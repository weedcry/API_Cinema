package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Voucher_DTO;
import com.QCINE.Main.Service.Voucher_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/voucher")
public class Ad_Voucher_Controller {
    @Autowired
    Voucher_Service voucher_service;

    @GetMapping("")
    public ResponseEntity getFilteredVoucher(@RequestParam(name = "state", required = false)Optional<String> state){
        try{
            List<Voucher_DTO> result = (List<Voucher_DTO>) voucher_service.getVoucherByState(state);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in Voucher_Controller - getFilteredVoucher: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getVoucher(@PathVariable("id") String id){
        try{
            Voucher_DTO result = (Voucher_DTO) voucher_service.getVoucher(id);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in Voucher_Controller - getVoucher: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity createVoucher(@RequestBody Voucher_DTO voucher_dto){
        try{
            if (voucher_service.createVoucher(voucher_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in Voucher_Controller - createVoucher: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity updateVoucher(@RequestBody Voucher_DTO voucher_dto){
        try{
            if (voucher_service.updateVoucher(voucher_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in Voucher_Controller - updateVoucher: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVoucher(@PathVariable("id") String id){
        try{
            if (voucher_service.deleteVoucher(id))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in Voucher_Controller - deleteVoucher: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}