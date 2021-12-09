package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.*;
import com.QCINE.Main.DTO.Filter.Lich_Filtered;
import com.QCINE.Main.Entity.*;
import com.QCINE.Main.Repository.Hoadon_Repository;
import com.QCINE.Main.Repository.Lich_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class Lich_Service {
    @Autowired
    Lich_Repository lich_repository;

    @Autowired
    Hoadon_Repository hoadon_repository;

    @Autowired
    Phong_Service phong_service;

    @Autowired
    ModelMapper mapper;

    public Lich_DTO getLichByIdLich(int idLich){
        Lich_DTO lich_dto = new Lich_DTO();
        try {
            System.out.println("abc");
            Lich_Entity lich = lich_repository.findByIdLich(idLich);
            System.out.println("abc "+lich.getNgay());
            lich_dto = mapper.map(lich,Lich_DTO.class);
            System.out.println("abc");
            return  lich_dto;
        }catch (Exception e){
            System.out.println("Exception "+e);
            return  lich_dto;
        }
    }

    public infoDetailLich_DTO getDetailLich(int idLich){
        infoDetailLich_DTO DetailLichDTO = new infoDetailLich_DTO();
        try {
            Lich_Entity lich = lich_repository.findByIdLich(idLich);
            List<HoaDon_Entity> listHoadon = hoadon_repository.findByLichEntityAndTinhTrang(lich,HoaDon_Entity.eTinhTrang.thanhCong);
            List<infoVitri_DTO> listVitri = new ArrayList<>();
            for(HoaDon_Entity hoaDon : listHoadon){
                for(CtHoaDon_Entity ctHoadon : hoaDon.getCtHoaDon_Entity()){
                    infoVitri_DTO vitri = new infoVitri_DTO();
                    vitri.setCot(ctHoadon.getCot());
                    vitri.setHang(ctHoadon.getHang());
                    listVitri.add(vitri);
                }
            }
            DetailLichDTO.setIdLich(idLich);
            DetailLichDTO.setIdPhong(lich.getPhongEntity().getIdPhong());
            DetailLichDTO.setTenPhong(lich.getPhongEntity().getTenPhong());
            DetailLichDTO.setTongGhe(lich.getPhongEntity().getSoCot()*lich.getPhongEntity().getSoHang());
            DetailLichDTO.setListVitriSD(listVitri);
        }catch (Exception e){
            System.out.println("Exception "+e);
        }
        return  DetailLichDTO;
    }


    public List<ListLichFromPhim_DTO> getLichPhimFromPhim(int idphim, String time, String idrap){
        List<Lich_Entity> listLich = new ArrayList<>();
        List<ListLichFromPhim_DTO> lichPhim = new ArrayList<>();
        try {
            Date datef = new SimpleDateFormat("dd-MM-yyyy").parse( time );
            if(idphim == 0){
                // get lich from rap and date

                try {
                    Thread.sleep(1000);
                    listLich = lich_repository.findByNgayAndIdrap(datef,idrap);
                }
                catch (Exception e){
                    System.err.println(e);
                }
            }else{
                // get lich from ngay , phim and rap

                try {
                    Thread.sleep(1000);
                    listLich = lich_repository.findByNgayAndIdphimAndIdrap(datef,idphim,idrap);
                }
                catch (Exception e){
                    System.err.println(e);
                }
            }

            //
            int idPhimCount = 0;
            int hourN = new Date().getHours();
            int minuteN = new Date().getMinutes();
            for (Lich_Entity lich_entity : listLich){
                if( lich_entity.getGio().getHours() <  hourN || (  lich_entity.getGio().getHours() == hourN &&
                        lich_entity.getGio().getMinutes()  < minuteN  )){
                    continue;
                }

                if(idPhimCount == lich_entity.getPhimEntity().getIdPhim()){
                    Lich_DTO lich_dto = mapper.map(lich_entity,Lich_DTO.class);
                    lichPhim.get(lichPhim.size() - 1).getLich().add(lich_dto);
                }else {
                    // mapper
                    Lich_DTO lich = mapper.map(lich_entity,Lich_DTO.class);

                    // set id phim
                    idPhimCount = lich_entity.getPhimEntity().getIdPhim();

                    ListLichFromPhim_DTO listLich_FromPhim_dto = new ListLichFromPhim_DTO();
                    listLich_FromPhim_dto.setIdPhim(lich_entity.getPhimEntity().getIdPhim());
                    listLich_FromPhim_dto.setTenPhim(lich_entity.getPhimEntity().getTenPhim());
                    listLich_FromPhim_dto.setLoaiPhim(lich_entity.getPhimEntity().getLoaiPhimEntity().getIdLoaiPhim());
                    listLich_FromPhim_dto.setPhanLoai(lich_entity.getPhimEntity().getPhanLoai());
                    listLich_FromPhim_dto.setDoTuoi(lich_entity.getPhimEntity().getDoTuoi().toString());
                    listLich_FromPhim_dto.setThoiLuong(lich_entity.getPhimEntity().getThoiLuong());
                    List<Lich_DTO> lich_dtos = new ArrayList<>();
                    lich_dtos.add(lich);
                    listLich_FromPhim_dto.setLich(lich_dtos);

                    lichPhim.add(listLich_FromPhim_dto);
                }
            }

        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
        return lichPhim;
    }

    public List<ListLichFromRap_DTO> getLichPhimFromRap(int idphim, String time, String idkhuvuc){
        List<Lich_Entity> listLich = new ArrayList<>();
        List<ListLichFromRap_DTO> lichPhim = new ArrayList<>();
        try {
            Date datef = new SimpleDateFormat("dd-MM-yyyy").parse( time );
                // get lich from ngay and phim and khu vuc
            try {
                Thread.sleep(1000);
                listLich = lich_repository.findByNgayAndIdphimAndIdKhuvuc(datef,idphim,idkhuvuc);
            }
            catch (Exception e){
                System.err.println(e);
            }
            //
            String idRapCount = "";
            int hourN = new Date().getHours();
            int minuteN = new Date().getMinutes();
            for (Lich_Entity lich_entity : listLich){
                if( lich_entity.getGio().getHours() <  hourN || (  lich_entity.getGio().getHours() == hourN &&
                        lich_entity.getGio().getMinutes()  < minuteN  )){
                    continue;
                }

                if(idRapCount == lich_entity.getPhongEntity().getRapEntity().getIdRap()){

                    Lich_DTO lich_dto = mapper.map(lich_entity, Lich_DTO.class);
                    lichPhim.get(lichPhim.size() - 1).getLich().add(lich_dto);
                } else {
                    // mapper
                    Lich_DTO lich = mapper.map(lich_entity, Lich_DTO.class);

                    // set id phim
                    idRapCount = lich_entity.getPhongEntity().getRapEntity().getIdRap();

                    ListLichFromRap_DTO listLichFromRap_dto= new ListLichFromRap_DTO();
                    listLichFromRap_dto.setIdRap(lich_entity.getPhongEntity().getRapEntity().getIdRap());
                    listLichFromRap_dto.setTenRap(lich_entity.getPhongEntity().getRapEntity().getTenRap());
                    listLichFromRap_dto.setIdKhuvuc(lich_entity.getPhongEntity().getRapEntity().getKhuvuc().getIdKhuVuc());
                    List<Lich_DTO> lich_dtos = new ArrayList<>();
                    lich_dtos.add(lich);
                    listLichFromRap_dto.setLich(lich_dtos);
                    lichPhim.add(listLichFromRap_dto);
                }
            }

        }catch (Exception e){
            System.out.println("Exception: "+e);
        }
        return lichPhim;
    }


//    Admin

    public Object getAllLichFiltered(int pageNum, int size, String idRap , Optional<Date> date, Optional<Time> time, String direction, String orderBy) {
        try {
            Sort sort;
            if (direction.toUpperCase().equals("ASC"))
                sort = new Sort(Sort.Direction.ASC, orderBy);
            else sort = new Sort(Sort.Direction.DESC, orderBy);

            Pageable pageable = new PageRequest(pageNum, size, sort);
            Page<Lich_Entity> pageLich = null;

            if (date.isPresent() && time.isPresent()) { //Get Lich in Date and time after...
                try {
                    Thread.sleep(1000);
                    pageLich = lich_repository.getAllByNgayAndGioAfter(date.get(), time.get(), pageable);
                }
                catch (Exception e){
                    System.err.println(e);
                }
            } else if (date.isPresent() && !time.isPresent()) { //Get Lich in Date after...
                try {
                    Thread.sleep(1000);
                    pageLich = lich_repository.getAllByNgayAfter(date.get(), pageable);
                }
                catch (Exception e){
                    System.err.println(e);
                }
            } else { //Get all Lich
                try {
                    Thread.sleep(1000);
                    pageLich = lich_repository.getAllByPhongEntityRapEntityIdRap(idRap, pageable);
                }
                catch (Exception e){
                    System.err.println(e);
                }
            }
            List<Lich_DTO> lich_dtos = new ArrayList<>();
            Lich_Filtered result = new Lich_Filtered();
            result.setCurrentPage(pageLich.getNumber()+1) ;
            result.setTotalPage(pageLich.getTotalPages());
            List<Lich_Entity> lich_entities = pageLich.getContent();
            if (!lich_entities.isEmpty()){
                for (Lich_Entity temp : lich_entities){
                    lich_dtos.add(mapper.map(temp, Lich_DTO.class));
                }
            }
            result.setFilteredLich(lich_dtos);

            return result;
        } catch (Exception e) {
            System.out.println("Error in Lich_Service - getAllLichFiltered Function: " + e);
            return "Failed";
        }
    }

    public List<Lich_DTO> getLichFilteredBy_Ngay_Phim_Rap(String idRap, Lich_DTO lich){
        List<Lich_DTO> result = new ArrayList<>();
        List<Lich_Entity> lich_entities = new ArrayList<>();
        try{
            try {
                Thread.sleep(1000);
                lich_entities = lich_repository.getLichFilteredBy_ngay_phim_rap(lich.getNgay(), lich.getPhimEntityIdPhim(), idRap);
            }
            catch (Exception e){
                System.err.println(e);
            }
            if (lich_entities!= null){
                for (Lich_Entity temp: lich_entities){
                    result.add(mapper.map(temp, Lich_DTO.class));
                }
            }
        } catch (Exception e){
            System.out.println("Error in Lich_Service - getLichFilteredBy_Ngay_Phim_Rap: "+e.getStackTrace());
        }

        return result;
    }

    public Object getLich(int idLich){
        try {

            return mapper.map(lich_repository.getOne(idLich), Lich_DTO.class);
        } catch (Exception e) {
            System.out.println("Error in Lich_Service - getLich Function: " + e);
            return "Failed";
        }
    }

    public boolean createLich(Lich_DTO lich_dto){
        try {
            lich_dto.setCreatedAt(new Date());
            lich_dto.setUpdatedAt(new Date());
            lich_repository.save(mapper.map(lich_dto, Lich_Entity.class));
            return true;
        } catch (Exception e) {
            System.out.println("Error in Lich_Service - getLich Function: " + e);
            return false;
        }
    }

    public boolean updateLich (Lich_DTO lich_dto){
        try {
            if (lich_repository.exists(lich_dto.getIdLich())){
                Lich_Entity temp = lich_repository.getOne(lich_dto.getIdLich());
                lich_dto.setCreatedAt(temp.getCreatedAt());
                lich_dto.setUpdatedAt(new Date());
                lich_repository.save(mapper.map(lich_dto, Lich_Entity.class));
                return true;
            } else return true;
        } catch (Exception e) {
            System.out.println("Error in Lich_Service - updateLich Function: " + e);
            return false;
        }
    }

    public boolean deleteLich (int idLich){
        try {
            if (lich_repository.exists(idLich)){
                lich_repository.delete(idLich);
                return true;
            } else return true;
        } catch (Exception e) {
            System.out.println("Error in Lich_Service - deleteLich Function: " + e);
            return false;
        }
    }




}
