package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Customer_DTO;
import com.QCINE.Main.DTO.Filter.Customer_Filtered;
import com.QCINE.Main.Service.Customer_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/customer")
public class Ad_Customer_Controller {
    @Autowired
    Customer_Service customer_service;

    @GetMapping("")
    public ResponseEntity index(){
        return new ResponseEntity("OKKKK", HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity getAllUserFilterd(@RequestParam(name = "pagenum", defaultValue = "0") int pageNum,
                                            @RequestParam(name = "size", defaultValue = "10") int size,
                                            @RequestParam(name="direction", defaultValue = "ASC") String direction,
                                            @RequestParam(name="orderby", defaultValue = "idCustomer") String orderBy){
        try{
            Customer_Filtered result = (Customer_Filtered) customer_service.getAllCustomer(pageNum, size, direction, orderBy);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in Customer_Controller - getAllUserFilterd: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomer(@PathVariable("id") int idCustomer){
        try{
            Customer_DTO result = (Customer_DTO) customer_service.getCustomer(idCustomer);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in Customer_Controller - getCustomer: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity createCustomer(@RequestBody Customer_DTO customer_dto){
        try{
            if (customer_service.createCustomer(customer_dto))
                return new ResponseEntity<Object>(HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in Customer_Controller - createCustomer: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity updateCustomer(@RequestBody Customer_DTO customer_dto){
        try{
            if (customer_service.updateCustomer(customer_dto))
                return new ResponseEntity<Object>(HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in Customer_Controller - updateCustomer: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") int idCustomer){
        try{
            if (customer_service.deleteCustomer(idCustomer))
                return new ResponseEntity<Object>(HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in Customer_Controller - deleteCustomer: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}