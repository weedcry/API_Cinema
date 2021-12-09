package com.QCINE.Main.CustomerController;

import com.QCINE.Main.DTO.Lich_DTO;
import com.QCINE.Main.DTO.ListLichFromPhim_DTO;
import com.QCINE.Main.DTO.ListLichFromRap_DTO;
import com.QCINE.Main.DTO.infoDetailLich_DTO;
import com.QCINE.Main.Service.Lich_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer/lich")
public class Lich_Controller {

    @Autowired
    Lich_Service lich_service;


    @GetMapping("/page")
    public ResponseEntity getListLich(
            @RequestParam(name = "phim", defaultValue = "0") int idphim,
            @RequestParam(name = "time", defaultValue = "0") String time,
            @RequestParam(name = "rap", defaultValue = "0") String  idrap,
            @RequestParam(name = "khuvuc", defaultValue = "0") String idkhuvuc){

        if(idkhuvuc.equals("0")){
            List<ListLichFromPhim_DTO> listLich = lich_service.getLichPhimFromPhim(idphim,time,idrap);
            System.out.println(" get list "+listLich.size());
            if(listLich.size() > 0){
                return ResponseEntity.status(HttpStatus.OK).body(listLich);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }else{
            List<ListLichFromRap_DTO> listLich = lich_service.getLichPhimFromRap(idphim,time,idkhuvuc);
            if(listLich.size() > 0){
                return ResponseEntity.status(HttpStatus.OK).body(listLich);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getPhongById(@PathVariable int id){
        Lich_DTO lich_dto = lich_service.getLichByIdLich(id);
        if(lich_dto == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(lich_dto);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity getDetailLich(@PathVariable int id){
        infoDetailLich_DTO Detaillich_dto = lich_service.getDetailLich(id);
        if(Detaillich_dto.getTongGhe() == 0){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
        return  ResponseEntity.status(HttpStatus.OK).body(Detaillich_dto);
    }


}
