package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Filter.User_Filtered;
import com.QCINE.Main.DTO.User_DTO;
import com.QCINE.Main.Service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("admin/user")
public class Ad_User_Controller {
    @Autowired
    User_Service user_service;

    @GetMapping("") //Return User_Filtered Object
    public ResponseEntity getAllUserFiltered( @RequestParam(name = "name", defaultValue = "") String name,
                                              @RequestParam(name = "pagenum", defaultValue = "0") int page,
                                              @RequestParam(name="size", defaultValue = "10") int size,
                                              @RequestParam(name="orderby", defaultValue = "name") String orderBy,
                                              @RequestParam(name = "direction", defaultValue = "ACS") String direction,
                                              @RequestParam(name = "role", required = false) Optional<Integer> idRole
    ){
        try{

            User_Filtered result = (User_Filtered) user_service.getAllFiltered(name, page, size, orderBy, direction, idRole);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in User_Controller - getAllUserFiltered: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") int id){
        try{
            User_DTO result = (User_DTO) user_service.getUser(id);
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Error in User_Controller - getUser: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity createUser(@RequestBody User_DTO user_dto){
        try{
            if (user_service.createUser(user_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in User_Controller - createUser: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public ResponseEntity updateUser(@RequestBody User_DTO user_dto){
        try{
            if (user_service.updateUser(user_dto))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in User_Controller - updateUser: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id){
        try{
            System.out.println("Delete user id received: "+ id);
            if (user_service.deleteUser(id))
                return new ResponseEntity<Object>("OK", HttpStatus.OK);
            else return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in User_Controller - deleteUser: "+e);
            return new ResponseEntity<Object>("Failed", HttpStatus.NO_CONTENT);
        }
    }
}