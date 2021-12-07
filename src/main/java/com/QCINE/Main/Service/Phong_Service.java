package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Lich_DTO;
import com.QCINE.Main.DTO.Phong_DTO;
import com.QCINE.Main.Entity.Lich_Entity;
import com.QCINE.Main.Entity.Phim_Entity;
import com.QCINE.Main.Entity.Phong_Entity;
import com.QCINE.Main.Entity.Rap_Entity;
import com.QCINE.Main.Repository.Lich_Repository;
import com.QCINE.Main.Repository.Phim_Repository;
import com.QCINE.Main.Repository.Phong_Repository;
import com.QCINE.Main.Repository.Rap_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Phong_Service {
    @Autowired
    Phong_Repository phong_repository;

    @Autowired
    Rap_Repository rap_repository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    Lich_Repository lich_repository;

    @Autowired
    Phim_Repository phim_repository;

    public Phong_DTO getPhongByIdphong(String idphong){
        Phong_DTO phong_dto = new Phong_DTO();
        try {
           Phong_Entity phong_entity = phong_repository.findByIdPhongAndTinhTrang(idphong,Phong_Entity.eTinhTrang.hoatDong);
           phong_dto = mapper.map(phong_entity,Phong_DTO.class);
           return  phong_dto;
        }catch (Exception e){
            System.out.println("Exception "+e);
            return  phong_dto;
        }
    }

    public List<Phong_Entity> getListPhongFromRap(String idrap){
        List<Phong_Entity> listPh = new ArrayList<>();
        try {
            listPh = phong_repository.getAllByRapEntity(rap_repository.findByIdRap(idrap));
        }catch (Exception e){
            System.out.println("Exception:" +e);
        }
        return listPh;
    }

    public List<Phong_DTO> getListPhongDTOFromRap(String idrap){
        List<Phong_DTO> listPh = new ArrayList<>();
        try {
            Rap_Entity rap_entity = rap_repository.findByIdRap(idrap);
            listPh = phong_repository.findByRapEntityAndTinhTrang(rap_entity,Phong_Entity.eTinhTrang.hoatDong).stream().map(
                    phong_entity -> {
                        Phong_DTO phong_dto = mapper.map(phong_entity,Phong_DTO.class);
                        return  phong_dto;
                    }).collect(Collectors.toList());
        }catch (Exception e){
            System.out.println("Exception:" +e);
        }
        return listPh;
    }

//    Admin

    public Object getAllPhong() {
        try {
            List<Phong_Entity> phong_entities = phong_repository.findAll();
            List<Phong_DTO> result = new ArrayList<>();
            for (Phong_Entity phong : phong_entities) {
                result.add(mapper.map(phong, Phong_DTO.class));
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in Phong_Service - getAllPhong Function: " + e);
            return false;
        }
    }

    public Object getAllPhongByRap(String idRap) {
        try {
            Rap_Entity rap = rap_repository.getOne(idRap);
            List<Phong_Entity> phong_entities = phong_repository.getAllByRapEntity(rap);
            List<Phong_DTO> result = new ArrayList<>();
            for (Phong_Entity phong : phong_entities) {
                result.add(mapper.map(phong, Phong_DTO.class));
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in Phong_Service - getAllPhong Function: " + e);
            return false;
        }
    }

    public Object getAllPhongByRapAndState(String idRap, String state) {
        try {
            List<Phong_DTO> result = new ArrayList<>();
            List<Phong_Entity> phong_entities = phong_repository.getAllByRapEntityIdRapAndTinhTrang(idRap, Phong_Entity.eTinhTrang.valueOf(state));
            for (Phong_Entity temp : phong_entities) {
                result.add(mapper.map(temp, Phong_DTO.class));
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in Phong_Service - getAllPhongByRapAndState: " + e.getStackTrace());
            return false;
        }
    }
    //
    public Object getUsablePhong(String idRap, Lich_DTO lich) {
        try {
            List<Phong_Entity> listPhong = phong_repository.getAllByRapEntityIdRapAndTinhTrang(idRap, Phong_Entity.eTinhTrang.valueOf("hoatDong"));
            System.out.println("Ngay: "+lich.getNgay());
            List<Lich_Entity> listLich = lich_repository.getAllByNgayAndPhongEntityRapEntityIdRap(lich.getNgay(), idRap);

            //Get Phim's startAt and endAt
            Phim_Entity phim_entity = phim_repository.getOne(lich.getPhimEntityIdPhim());
            Time phimEnd_At = new Time(0);
            phimEnd_At.setTime(lich.getGio().getTime()+ phim_entity.getThoiLuong()*60000);
            for (Lich_Entity tempLich : listLich) {
                Time tempTo = new Time(0);
                tempTo.setTime(tempLich.getGio().getTime() + tempLich.getPhimEntity().getThoiLuong() * 60000);

                if ((lich.getGio().getTime()<=tempLich.getGio().getTime()) &&(phimEnd_At.getTime()>=lich.getGio().getTime())){
                    listPhong.remove(tempLich.getPhongEntity());
                }

                if((lich.getGio().getTime()<=tempTo.getTime()) && (phimEnd_At.getTime()>=tempLich.getGio().getTime())){
                    listPhong.remove(tempLich.getPhongEntity());
                }
            }
            List<Phong_DTO> result = new ArrayList<>();
            if (!listPhong.isEmpty()){
                for(Phong_Entity temp : listPhong){
                    result.add(mapper.map(temp, Phong_DTO.class));
                }
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in Phong_Service - getUsablePhong: " + e.getStackTrace());
            return false;
        }
    }

    public Object getPhong(String idPhong) {
        try {
            return mapper.map(phong_repository.findOne(idPhong), Phong_DTO.class);
        } catch (Exception e) {
            System.out.println("Error in Phong_Service - getPhong Function: " + e);
            return false;
        }
    }

    public boolean createPhong(Phong_DTO phong_dto) {
        try {
            if (phong_repository.exists(phong_dto.getIdPhong())) {
                return false;
            } else {
                phong_repository.save(mapper.map(phong_dto, Phong_Entity.class));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in Phong_Service - createPhong Function: " + e);
            return false;
        }
    }

    public boolean updatePhong(Phong_DTO phong_dto) {
        try {
            if (!phong_repository.exists(phong_dto.getIdPhong())) {
                return false;
            } else {
                phong_repository.save(mapper.map(phong_dto, Phong_Entity.class));
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in Phong_Service - updatePhong Function: " + e);
            return false;
        }
    }

    public boolean deletePhong(String idPhong) {
        try {
            if (!phong_repository.exists(idPhong)) {
                return false;
            } else {
                phong_repository.delete(idPhong);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error in Phong_Service - deletePhong Function: " + e);
            return false;
        }
    }

}

