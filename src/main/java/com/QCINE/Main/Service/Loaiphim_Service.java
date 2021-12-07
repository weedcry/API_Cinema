package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.LoaiPhim_DTO;
import com.QCINE.Main.Entity.LoaiPhim_Entity;
import com.QCINE.Main.Repository.Loaiphim_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class Loaiphim_Service {
    @Autowired
    Loaiphim_Repository loaiPhim_repository;

    @Autowired
    ModelMapper mapper;


    public List<LoaiPhim_DTO> getListLoaiPhim(){
        List<LoaiPhim_DTO> listloaiphimDTO = new ArrayList<>();
        try {
            listloaiphimDTO = loaiPhim_repository.findAll().stream().map(
                    loaiPhim_entity -> {
                        LoaiPhim_DTO loaiPhim_dto = mapper.map(loaiPhim_entity,LoaiPhim_DTO.class);
                        return  loaiPhim_dto;
                    }).collect(Collectors.toList());
            return  listloaiphimDTO;
        }catch (Exception e){
            System.out.println("Exception "+e);
        }
        return  listloaiphimDTO;
    }

//    Admin

    public Object findAll() {
        try {
            List<LoaiPhim_Entity> loaiPhim_entities = loaiPhim_repository.findAll();
            List<LoaiPhim_DTO> result = new ArrayList<>();
            for (LoaiPhim_Entity loaiPhimEntity : loaiPhim_entities) {
                result.add(mapper.map(loaiPhimEntity, LoaiPhim_DTO.class));
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Service - findAll Function: " + e);
            return "Failed";
        }
    }

    public Object findLoaiPhim(String idLoai) {
        try {
            return mapper.map(loaiPhim_repository.findOne(idLoai), LoaiPhim_DTO.class);
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Service - findLoaiPhim Function: " + e);
            return "Failed";
        }
    }

    public boolean createLoaiPhim(LoaiPhim_DTO loaiPhim_dto) {
        try {
            if (loaiPhim_repository.exists(loaiPhim_dto.getIdLoaiPhim()))
                return false;
            else {
                loaiPhim_repository.save(mapper.map(loaiPhim_dto, LoaiPhim_Entity.class));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Service - createLoaiPhim Function: " + e);
            return false;
        }
    }

    public boolean updateLoaiPhim(LoaiPhim_DTO loaiPhim_dto) {
        try {
            if (loaiPhim_repository.exists(loaiPhim_dto.getIdLoaiPhim())) {
                loaiPhim_repository.save(mapper.map(loaiPhim_dto, LoaiPhim_Entity.class));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Service - updateLoaiPhim Function: " + e);
            return false;
        }
    }

    public boolean deleteLoaiPhim(String idLoai){
        try {
            if (loaiPhim_repository.exists(idLoai)) {
                loaiPhim_repository.delete(idLoai);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error in LoaiPhim_Service - deleteLoaiPhim Function: " + e);
            return false;
        }
    }
}
