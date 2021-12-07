package com.QCINE.Main.AdminController;

import com.QCINE.Main.DTO.Week_DTO;
import com.QCINE.Main.Service.Week_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/week")
public class Ad_Week_Controller {
    @Autowired
    Week_Service week_service;

    @GetMapping("")
    public ResponseEntity getAllDay(){
        try{
            List<Week_DTO> result = (List<Week_DTO>) week_service.getAllDay();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch(Exception e){
            System.out.println("Error in Week_Controller - getAllDay func: "+e.getStackTrace());
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getDay(@PathVariable("id") String id){
        try{
            Week_DTO week_dto = (Week_DTO) week_service.getDay(id);
            return new ResponseEntity(week_dto,HttpStatus.OK);
        } catch(Exception e ){
            System.out.println("Error in Week_Controller - getDay func: "+ e.getStackTrace() );
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("")
    public ResponseEntity createDay(@RequestBody Week_DTO week_dto){
        try{
            if (week_service.createDay(week_dto))
                return new ResponseEntity(HttpStatus.OK);
            else return  new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            System.out.println("Error in Week_Controller - create day func: "+ e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("")
    public  ResponseEntity updateDay(@RequestBody Week_DTO week_dto){
        try{
            if (week_service.updateDay(week_dto))
                return new ResponseEntity(HttpStatus.OK);
            else return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            System.out.println("Error in Week_Controller - updateDay func: "+ e.getStackTrace());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDay(@PathVariable("id") String id){
        try{
            if (week_service.deleteDay(id))
                return new ResponseEntity(HttpStatus.OK);
            else return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            System.out.println("Error in Week_Controller - deleteDay func: "+ e.getStackTrace() );
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}