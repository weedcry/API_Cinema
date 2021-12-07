package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.KhuVuc_DTO;
import com.QCINE.Main.DTO.Rap_DTO;
import com.QCINE.Main.Entity.KhuVuc_Entity;
import com.QCINE.Main.Entity.Rap_Entity;
import com.QCINE.Main.Repository.KhuVuc_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KhuVuc_Service {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    KhuVuc_Repository khuVuc_repository;

    @Autowired
    Rap_Service rap_service;

    public List<KhuVuc_DTO> getAllKhuvuc() {
        List<KhuVuc_DTO> listKhuvuc = new ArrayList<>();

        try {
            listKhuvuc = parseKhuvucEntitytoDTO(khuVuc_repository.findAll());
            return  listKhuvuc;

        }catch (Exception e){
            System.out.println("Exception "+e);
            return  listKhuvuc;
        }
    }

    public List<KhuVuc_DTO> parseKhuvucEntitytoDTO(List<KhuVuc_Entity> listKhuvuc){
        List<KhuVuc_DTO>  listKhuvucDTO = khuVuc_repository.findAll().stream().map(
                khuVuc_entity -> {
                    KhuVuc_DTO khuVuc_dto = mapper.map(khuVuc_entity,KhuVuc_DTO.class);

                    List<Rap_Entity> listRap = new ArrayList<>(khuVuc_entity.getRapEntity());
                    List<Rap_DTO> listRapDTO =  listRap.stream().map(
                            rap_entity -> {
                                Rap_DTO rap_dto = mapper.map(rap_entity,Rap_DTO.class);
                                return  rap_dto;
                            }).collect(Collectors.toList());

                    khuVuc_dto.setListRap(listRapDTO);
                    return  khuVuc_dto;
                }).collect(Collectors.toList());
        return  listKhuvucDTO;
    }


//    Admin

    public Object findAll() {
        try {
            List<KhuVuc_Entity> listEntity = khuVuc_repository.findAll();
            List<KhuVuc_DTO> result = new ArrayList<>();
            for (KhuVuc_Entity khuVuc : listEntity) {
                result.add(mapper.map(khuVuc, KhuVuc_DTO.class));
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Service - findAll Function: " + e);
            return "Failed";
        }
    }

    public Object findById(String idKhuVuc) {
        try {
            KhuVuc_Entity khuVuc = khuVuc_repository.findByIdKhuVuc(idKhuVuc);
            KhuVuc_DTO result = mapper.map(khuVuc, KhuVuc_DTO.class);
            return result;
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Service - findById Function: " + e);
            return "Failed";
        }
    }
    public Object findById_Entity(String idKhuVuc) {
        try {
            KhuVuc_Entity khuVuc = khuVuc_repository.findByIdKhuVuc(idKhuVuc);
            return khuVuc;
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Service - findById Function: " + e);
            return "Failed";
        }
    }

    public boolean updateKhuVuc(KhuVuc_DTO khuVuc) {
        try {
            if (khuVuc_repository.exists(khuVuc.getIdKhuVuc())) {

                khuVuc_repository.save(mapper.map(khuVuc, KhuVuc_Entity.class));
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Service - updateKhuVuc Function: " + e);
            return false;
        }
    }

    public boolean createKhuVuc(KhuVuc_DTO khuVuc_dto) {
        try {
            if (khuVuc_repository.exists(khuVuc_dto.getIdKhuVuc())) {
                return false;
            } else {
                khuVuc_repository.save(mapper.map(khuVuc_dto, KhuVuc_Entity.class));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Service - createKhuVuc Function: " + e);
            return false;
        }
    }

    public boolean deleteKhuVuc(String idKhuVuc) {
        try {
            khuVuc_repository.delete(idKhuVuc);
            return true;
        } catch (Exception e) {
            System.out.println("Error in KhuVuc_Service - deleteKhuVuc Function: " + e.toString());
            return false;
        }
    }
}
