package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Voucher_DTO;
import com.QCINE.Main.Entity.Customer_Entity;
import com.QCINE.Main.Entity.HoaDon_Entity;
import com.QCINE.Main.Entity.Voucher_Entity;
import com.QCINE.Main.Repository.Hoadon_Repository;
import com.QCINE.Main.Repository.Voucher_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Component
public class Voucher_Service {

    @Autowired
    Voucher_Repository voucher_repository;

    @Autowired
    Hoadon_Repository hoadon_repository;

    @Autowired
    Customer_Service customer_service;

    @Autowired
    ModelMapper mapper;

    public List<Voucher_DTO> getAllVoucherHieuluc(){
        List<Voucher_DTO> listVoucherHieuluc = new ArrayList<>();

        try {
            listVoucherHieuluc = voucher_repository.findByTinhTrang(Voucher_Entity.eTinhTrang.conHieuLuc).stream().map(
                voucher_entity ->{
                    Voucher_DTO voucher_dto = mapper.map(voucher_entity,Voucher_DTO.class);
                    return  voucher_dto;
                }).collect(Collectors.toList());

            return  listVoucherHieuluc;

        }catch (Exception e){
            System.out.println("Exception "+e);
            return  listVoucherHieuluc;
        }
    }


    public Voucher_DTO CheckVoucherUsed (String username, String id) {
        Voucher_DTO voucher_dto = new Voucher_DTO();
        try {
            Customer_Entity customer_entity = customer_service.findCustomerEntityByUsername(username);
            Voucher_Entity voucher_entity = voucher_repository.findByIdVoucher(id);

            //check null
            if(ObjectUtils.isEmpty(customer_entity) || ObjectUtils.isEmpty(voucher_entity)){
                return voucher_dto;
            }

            //check date
            Date timeN = new Date();
            if(voucher_entity.getEndDate().getTime() < timeN.getTime() ){
                return  voucher_dto;
            }

            List<HoaDon_Entity> list = hoadon_repository.findByCustomerEntityAndVoucherEntity(customer_entity,voucher_entity);
            if(list.size() > voucher_entity.getSoLanSD()){
                return  voucher_dto;
            }
            voucher_dto = mapper.map(voucher_entity,Voucher_DTO.class);
            return  voucher_dto;
        } catch (Exception e){
            System.out.println("Exception "+e);
            return  voucher_dto;
         }
    }


//    Admin

    public Object getVoucherByState(Optional<String> state){
        try{
            List<Voucher_Entity> voucher_entities = new ArrayList<>();
            if (state.isPresent()){
                voucher_entities = voucher_repository.findAllByTinhTrang(Voucher_Entity.eTinhTrang.valueOf(state.get()));
            } else voucher_entities = voucher_repository.findAll();

            List<Voucher_DTO> result = new ArrayList<>();
            for (Voucher_Entity voucher: voucher_entities){
                result.add(mapper.map(voucher, Voucher_DTO.class));
            }
            return result;
        } catch (Exception e){
            System.out.println("Error in Voucher_Service - getVoucherByState Function: " + e);
            return false;
        }
    }

    public Object getVoucher(String idVoucher){
        try{
            return mapper.map(voucher_repository.getOne(idVoucher), Voucher_DTO.class);
        } catch (Exception e){
            System.out.println("Error in Voucher_Service - getVoucher Function: " + e);
            return false;
        }
    }

    public boolean createVoucher(Voucher_DTO voucher_dto){
        try{
            voucher_repository.save(mapper.map(voucher_dto, Voucher_Entity.class));
            return true;
        } catch (Exception e){
            System.out.println("Error in Voucher_Service - createVoucher Function: " + e);
            return false;
        }
    }

    public boolean updateVoucher(Voucher_DTO voucher_dto){
        try{
            if (voucher_repository.exists(voucher_dto.getIdVoucher())){
                voucher_repository.save(mapper.map(voucher_dto, Voucher_Entity.class));
                return true;
            } else return false;
        } catch (Exception e){
            System.out.println("Error in Voucher_Service - updateVoucher Function: " + e);
            return false;
        }
    }

    public boolean deleteVoucher(String idVoucher){
        try{
            if (voucher_repository.exists(idVoucher)){
                voucher_repository.delete(idVoucher);
                return true;
            } else return false;
        } catch (Exception e){
            System.out.println("Error in Voucher_Service - deleteVoucher Function: " + e);
            return false;
        }
    }

}
