package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Rap_DTO;
import com.QCINE.Main.Entity.KhuVuc_Entity;
import com.QCINE.Main.Entity.Rap_Entity;
import com.QCINE.Main.Repository.KhuVuc_Repository;
import com.QCINE.Main.Repository.Rap_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Rap_Service {
    @Autowired
    Rap_Repository rap_repository;

    @Autowired
    KhuVuc_Repository khuVuc_repository;

    @Autowired
    ModelMapper mapper;

    public List<Rap_DTO> getAllRapByKhuVuc(KhuVuc_Entity khuVuc_entity){
        List<Rap_DTO> result = new ArrayList<>();
        try {
            result = rap_repository.findAllByKhuvucAndTinhTrang(khuVuc_entity, Rap_Entity.eTinhTrang.hoatDong).stream().map(
                    rap_entity -> {
                        Rap_DTO rap_dto = mapper.map(rap_entity,Rap_DTO.class);
                        return  rap_dto;
                    }).collect(Collectors.toList());
            return  result;
        }catch (Exception e){
            System.out.println("Exception "+e);
            return  result;
        }
    }


    public Rap_DTO getRapbyId(String id){
        Rap_DTO rap_dto = new Rap_DTO();
        try {
            rap_dto = mapper.map(rap_repository.findByIdRap(id),Rap_DTO.class);
        }catch (Exception e){
            System.out.println("Exception "+e);
        }
        return rap_dto;
    }

    public List<Rap_DTO> getAllRapByPhimAndTime(int idphim, String time){
        List<Rap_DTO> listRap = new ArrayList<>();
        try{
            Date timeN = new SimpleDateFormat("dd-MM-yyyy").parse(time);
            listRap = rap_repository.findByDateAndIdPhim(timeN,idphim).stream().map(
                    rap_entity -> {
                        Rap_DTO rap_dto = mapper.map(rap_entity,Rap_DTO.class);
                        return rap_dto;
                    }).collect(Collectors.toList());
        }catch (Exception e){
            System.out.println("Exception "+e);
        }
        return listRap;
    }


//    Admin

    public Object getAll() {
        try {
            List<Rap_Entity> rap_entities = rap_repository.findAll();
            List<Rap_DTO> result = new ArrayList<>();
            for (Rap_Entity rap : rap_entities) {
                Rap_DTO temp = mapper.map(rap, Rap_DTO.class);
                result.add(temp);

            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in Rap_Service - getAll Function: " + e);
            return "Failed";
        }
    }

    public Object getByKhuVuc(String idKhuVuc){
        try {
            KhuVuc_Entity khuVuc = khuVuc_repository.findByIdKhuVuc(idKhuVuc);
            List<Rap_Entity> rap_entities = rap_repository.findAllByKhuvuc(khuVuc);
            List<Rap_DTO> result = new ArrayList<>();
            for (Rap_Entity rap : rap_entities) {
                Rap_DTO temp = mapper.map(rap, Rap_DTO.class);
                result.add(temp);

            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in Rap_Service - getByKhuVuc Function: " + e);
            return "Failed";
        }
    }

    public Object getById(String idRap) {
        try {
            Rap_Entity rap = rap_repository.findOne(idRap);
            Rap_DTO temp = mapper.map(rap, Rap_DTO.class);
            return temp;
        } catch (Exception e) {
            System.out.println("Error in Rap_Service - getById Function: " + e);
            return "Failed";
        }
    }

    public boolean updateRap(Rap_DTO rap_dto) {
        try {
            if (rap_repository.exists(rap_dto.getIdRap())) {
                Rap_Entity rap = mapper.map(rap_dto, Rap_Entity.class);
                rap_repository.save(rap);
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in Rap_Service - updateRap Function: " + e);
            return false;
        }
    }

    public boolean createRap(Rap_DTO rap_dto) {
        try {
            if (rap_repository.exists(rap_dto.getIdRap())) {
                return false;
            } else {
                rap_repository.save(mapper.map(rap_dto, Rap_Entity.class));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in Rap_Service - createRap Function: " + e);
            return false;
        }
    }

    public boolean deleteRap(String idRap) {
        try {
            if (rap_repository.exists(idRap)) {
                rap_repository.delete(idRap);
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in Rap_Service - deleteRap Function: " + e);
            return false;
        }
    }

}
