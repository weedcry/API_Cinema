package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Filter.Phim_Filtered;
import com.QCINE.Main.DTO.Phim_DTO;
import com.QCINE.Main.DTO.ResultPage;
import com.QCINE.Main.Entity.Phim_Entity;
import com.QCINE.Main.Repository.Phim_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class Phim_Service {

    @Autowired
    Phim_Repository phim_repository;

    @Autowired
    ModelMapper mapper;

    @Autowired
    Lich_Service lich_service;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private String jwtExpirationMs;


    public Phim_DTO getPhimByIdphim(int idphim){
        Phim_DTO phim_dto = new Phim_DTO();
        try {
            Phim_Entity phim_entity = phim_repository.findByIdPhim(idphim);
            phim_dto = mapper.map(phim_entity,Phim_DTO.class);
            phim_dto.setDoTuoi(phim_entity.getDoTuoi().toString());
            return  phim_dto;
        }catch (Exception e){
            System.out.println("Exception "+e);
            return  phim_dto;
        }
    }



    public ResultPage getListPhimPagination(int page, int size,String status,String time, String idrap){
        ResultPage resultPage = new ResultPage();
        try {
            Sort sort = new Sort(Sort.Direction.ASC, "ngayKC");
            Pageable pageable = new PageRequest(page - 1,size,sort);

            Phim_Entity.eTrangThai tinhtrang ;
            if(status.equals("DANGCHIEU")){
                tinhtrang = Phim_Entity.eTrangThai.dangChieu;
            }else{
                tinhtrang = Phim_Entity.eTrangThai.sapChieu;
            }
            Page<Phim_Entity> Result = null;
            if(time.equals("0") && idrap.equals("0")){
                Result = phim_repository.findByTrangThai(tinhtrang, pageable);

                // parse list
                List<Phim_DTO> listPhimDTO = Result.getContent().stream().map(
                        phim_entity -> {
                            Phim_DTO  phim_dto = mapper.map(phim_entity,Phim_DTO.class);
                            phim_dto.setDoTuoi(phim_entity.getDoTuoi().toString());
                            return  phim_dto;
                        }).collect(Collectors.toList());

                resultPage.setPage(Result.getNumber()  + 1 );
                resultPage.setListResult(listPhimDTO);
                resultPage.setTotalpage(Result.getTotalPages());

            }else{
                Date datef = new SimpleDateFormat("dd-MM-yyyy").parse( time );
                List<Phim_DTO> listPhimDTO = phim_repository.findPhimByNgayKCAndIdRap(datef,idrap).stream().map(
                        phim_entity -> {
                            Phim_DTO  phim_dto = mapper.map(phim_entity,Phim_DTO.class);
                            phim_dto.setDoTuoi(phim_entity.getDoTuoi().toString());
                            return  phim_dto;
                        }).collect(Collectors.toList());

                resultPage.setPage(0);
                resultPage.setListResult(listPhimDTO);
                resultPage.setTotalpage(0);
            }

        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
        return resultPage;
    }


//    Admin

    public Object getAllPhim() {
        try {
            Pageable pageable = new PageRequest(0, 10);
            List<Phim_Entity> phim_entities = phim_repository.findAll(pageable).getContent();
            List<Phim_DTO> result = new ArrayList<>();
            for (Phim_Entity phim : phim_entities) {
                System.out.println("Date: " + phim.getNgayKC());
                result.add(mapper.map(phim, Phim_DTO.class));
            }
            return result;
        } catch (Exception e) {
            System.out.println("Error in Phim_Service - getAllPhim Function: " + e);
            return false;
        }
    }

    public Object getAllPhim_followedByState(String state){

        List<Phim_DTO> result = new ArrayList<>();
        try {
            List<Phim_Entity> phim_entities = phim_repository.findAllByTrangThai(Phim_Entity.eTrangThai.valueOf(state));
            if (!phim_entities.isEmpty()){
                for (Phim_Entity temp: phim_entities){
                    result.add(mapper.map(temp, Phim_DTO.class));
                }
            }
        } catch (Exception e){
            System.out.println("Error in Phim_Service - getAllPhim_followedByState: "+ e.getStackTrace());
        }
        return  result;
    }

    public Object getPhim(int idPhim) {
        try {
            Phim_Entity phim_entity = phim_repository.findOne(idPhim);
            return mapper.map(phim_entity, Phim_DTO.class);
        } catch (Exception e) {
            System.out.println("Error in Phim_Service - getPhim Function: " + e);
            return false;
        }
    }

    public Object getPhimFiltered(String name, int pageNum, int size, String orderBy, String direction, Optional<String> state) {
        try {
            //Create Object
            Page<Phim_Entity> phim_entities;
            Phim_Filtered result = new Phim_Filtered();
            Sort sort;

            //Set Order direction
            if (direction.toUpperCase().equals("ASC")) {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            }
            Pageable page = new PageRequest(pageNum, size, sort);

            //Query Phim
            if (state.isPresent()) {
                phim_entities = phim_repository.findAllByTrangThaiAndTenPhimContaining(Phim_Entity.eTrangThai.valueOf(state.get()), name, page);
            } else
                phim_entities = phim_repository.findAllByTenPhimContaining(name, page);

            //Convert Phim_Entity to Phim_DTO
            List<Phim_DTO> phim_dtos = new ArrayList<>();
            for (Phim_Entity phim : phim_entities.getContent()) {
                phim_dtos.add(mapper.map(phim, Phim_DTO.class));
            }

            //Set value to Result object - Passing to Controller
            result.setTotalPage(phim_entities.getTotalPages());
            result.setCurrentPage(phim_entities.getNumber() + 1);
            result.setResultPhim(phim_dtos);
            return result;
        } catch (Exception e) {
            System.out.println("Error in Phim_Service - getPhimFiltered Function: " + e);
            return false;
        }
    }

    public boolean createPhim(Phim_DTO phim_dto) {
        try {
            phim_dto.setCreatedAt(new Date());

            phim_dto.setUpdatedAt(new Date());
            phim_repository.save(mapper.map(phim_dto, Phim_Entity.class));
            return true;
        } catch (Exception e) {
            System.out.println("Error in Phim_Service - createPhim Function: " + e);
            return false;
        }
    }

    public boolean updatePhim(Phim_DTO phim_dto) {
        try {
            Phim_Entity temp = phim_repository.findOne(phim_dto.getIdPhim());
            phim_dto.setCreatedAt(temp.getCreatedAt());
            phim_dto.setUpdatedAt(new Date());
            phim_repository.save(mapper.map(phim_dto, Phim_Entity.class));
            return true;
        } catch (Exception e) {
            System.out.println("Error in Phim_Service - updatePhim Function: " + e);
            return false;
        }
    }

    public boolean deletePhim(int idPhim) {
        try {
            phim_repository.delete(idPhim);
            return true;
        } catch (Exception e) {
            System.out.println("Error in Phim_Service - deletePhim Function: " + e);
            return false;
        }
    }

}
