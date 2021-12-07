package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.CtHoaDon_DTO;
import com.QCINE.Main.Entity.CtHoaDon_Entity;
import com.QCINE.Main.Repository.CtHoadon_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CtHoaDon_Service {
    @Autowired
    ModelMapper mapper;

    @Autowired
    CtHoadon_Repository ctHoaDon_repository;

    public Object getCtHoaDonFollowedByHoaDon(int idHoaDon){
        try {
            List<CtHoaDon_Entity> ctHoaDon_entities = ctHoaDon_repository.getAllByHoaDonEntity_IdHoaDon(idHoaDon);
            List<CtHoaDon_DTO> result = new ArrayList<>();
            for(CtHoaDon_Entity temp: ctHoaDon_entities){
                result.add(mapper.map(temp, CtHoaDon_DTO.class));
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in CtHoaDon_Service - getCtHoaDonFollowedByHoaDon Function: " + e);
            return false;
        }
    }

    public boolean createCtHoaDon(CtHoaDon_DTO ctHoaDon_dto){
        try {
            ctHoaDon_repository.save(mapper.map(ctHoaDon_dto, CtHoaDon_Entity.class));
            return true;
        } catch (Exception e) {
            System.out.println("Error in CtHoaDon_Service - createCtHoaDon Function: " + e);
            return false;
        }
    }

    public boolean deleteCtHoaDonFollowedByHoaDon(int idHoaDon){
        try {
            if(ctHoaDon_repository.deleteAllByHoaDonEntity_IdHoaDon(idHoaDon))
                return true;
            else return false;
        } catch (Exception e) {
            System.out.println("Error in CtHoaDon_Service - deleteCtHoaDonFollowedByHoaDon Function: " + e);
            return false;
        }
    }
}