package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.*;
import com.QCINE.Main.DTO.Filter.HoaDon_Filtered;
import com.QCINE.Main.Entity.*;
import com.QCINE.Main.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Component
public class Hoadon_Service {

    @Autowired
    Hoadon_Repository hoaDon_repository;

    @Autowired
    Customer_Service customer_service;

    @Autowired
    ModelMapper mapper;

    @Autowired
    Voucher_Repository voucher_repository;

    @Autowired
    Lich_Repository lich_repository;

    @Autowired
    ThanhToan_Repository thanhToan_repository;

    @Autowired
    CtHoadon_Repository ctHoadon_repository;

    @Autowired
    Customer_Repository customer_repository;

    @Autowired
    SendGridMail_Service sendGridMailService;


    public List<HoaDon_DTO> getHoadonCustomer(String username){
        List<HoaDon_DTO> listHoadonDTO = new ArrayList<>();
        try {
            Customer_Entity customer_entity = customer_service.findCustomerEntityByUsername(username);
            listHoadonDTO = hoaDon_repository.findByCustomerEntity(customer_entity).stream().map(
                    hoaDon_entity -> {
                        HoaDon_DTO hoaDon_dto = mapper.map(hoaDon_entity,HoaDon_DTO.class);
                        // thanh toan
                        ThanhToan_DTO thanhToan_dto = mapper.map(hoaDon_entity.getThanhToanEntity(),ThanhToan_DTO.class);
                        hoaDon_dto.setThanhToan(thanhToan_dto);
                        // lich
                        Lich_DTO lich_dto = mapper.map(hoaDon_entity.getLichEntity(),Lich_DTO.class);
                        hoaDon_dto.setLich(lich_dto);
                        // ct hóa đơn
                        List<CtHoaDon_DTO> listCTHD = hoaDon_entity.getCtHoaDon_Entity().stream().map(
                                ctHoaDon_entity -> {
                                    CtHoaDon_DTO ctHoaDon_dto = mapper.map(ctHoaDon_entity,CtHoaDon_DTO.class);
                                    return  ctHoaDon_dto;
                                }).collect(Collectors.toList());
                        hoaDon_dto.setListCTHoadon(listCTHD);
                        return hoaDon_dto;
                    }).collect(Collectors.toList());
            Collections.reverse(listHoadonDTO);
            return listHoadonDTO;
        }catch (Exception e){
            System.out.println("Exception "+e);
            return listHoadonDTO;
        }
    }

    @Transactional
    public Boolean CreateHoadon(infoThanhtoan_DTO infoThanhtoan, String username){
        HoaDon_Entity hoaDon_entity = new HoaDon_Entity();
        float giamgia = 0;
        try {
            hoaDon_entity.setIdUser(infoThanhtoan.getIdUser());
            hoaDon_entity.setCreatedAt(infoThanhtoan.getCreatedAt());
            hoaDon_entity.setTinhTrang(HoaDon_Entity.eTinhTrang.thanhCong);

            // set customer
            Customer_Entity customer_entity = customer_service.findCustomerEntityByUsername(username);
            hoaDon_entity.setCustomerEntity(customer_entity);

            // set ct hoa don
            float total = 0;
            for (CtHoaDon_DTO ctHoaDon_dto : infoThanhtoan.getListCTHoadon()){
                total+=ctHoaDon_dto.getGia();
            }

            // setVoucher
            if(infoThanhtoan.getIdVoucher().length() > 0){
                Voucher_Entity voucher_entity = voucher_repository.findByIdVoucher(infoThanhtoan.getIdVoucher());
                if(ObjectUtils.isEmpty(voucher_entity)){
                    System.out.println("Voucher null");
                    return  false;
                }
                hoaDon_entity.setVoucherEntity(voucher_entity);
                giamgia += (total*voucher_entity.getPercentageOff()) / 100;
                System.out.println(giamgia);
            }
            giamgia += infoThanhtoan.getUsedPoint();

            // set lich
            Lich_Entity lich_entity = lich_repository.findByIdLich(infoThanhtoan.getIdlich());
            hoaDon_entity.setLichEntity(lich_entity);

            if(ObjectUtils.isEmpty(lich_entity) || ObjectUtils.isEmpty(customer_entity)){
                System.out.println("Lich null");
                return  false;
            }

            if(!checkghetrung(lich_entity,infoThanhtoan.getListCTHoadon())){
                System.out.println("seat trung");
                return  false;
            }

//            //check thanh toan
//            if(infoThanhtoan.getHinhThuc().equals("PAYPAL")){
//                if(ObjectUtils.isEmpty(thanhToan_repository.findByMoTa(infoThanhtoan.getMotaThanhtoan()))){
//                    return  false;
//                }
//            }

            // set thanh toan
            ThanhToan_Entity   thanhToan_entity = new ThanhToan_Entity();
            thanhToan_entity.setHinhThuc(infoThanhtoan.getHinhThuc());
            thanhToan_entity.setMoTa(infoThanhtoan.getMotaThanhtoan());
            hoaDon_entity.setThanhToanEntity(thanhToan_entity);

            //set total
            float totalN = total - giamgia;
            if(totalN < 0){
                totalN = 0;
            }
            hoaDon_entity.setTotal(totalN);

            //set point
            hoaDon_entity.setUsedPoint(infoThanhtoan.getUsedPoint());

            HoaDon_Entity finalHD = hoaDon_repository.save(hoaDon_entity);

            String ghe = "";
            for (CtHoaDon_DTO ctHoaDon_dto : infoThanhtoan.getListCTHoadon()){
                CtHoaDon_Entity ctHoaDon_entity = mapper.map(ctHoaDon_dto,CtHoaDon_Entity.class);
                ctHoaDon_entity.setHoaDonEntity(finalHD);
                ctHoadon_repository.save(ctHoaDon_entity);
                String hang = String.valueOf(Character.toChars(ctHoaDon_dto.getHang() + 64));
                ghe+= hang + ctHoaDon_dto.getCot()+",";
            }

            // coin
            if(infoThanhtoan.getUsedPoint() > 0){
                int coinN = customer_entity.getPromoPoint() - infoThanhtoan.getUsedPoint();
                customer_entity.setPromoPoint(coinN);
                customer_repository.save(customer_entity);
            }

            int point = customer_entity.getPromoPoint() + 1000;
            customer_entity.setPromoPoint(point);
            customer_repository.save(customer_entity);

            //send mail

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:MM:ss");
            String dateCre = formatter.format(finalHD.getCreatedAt());

            sendGridMailService.sendTemplateMail("hoadon",customer_entity.getUserEntity().getEmail(),
                    customer_entity.getFirstName(),"",lich_entity.getIdLich(),dateCre, ghe,
                    infoThanhtoan.getListCTHoadon().size()+"",total+"", giamgia+"",totalN+"");

            System.out.println("thanh cong");

            return true;
        }catch (Exception e){
            System.out.println("Exception hd "+e.getMessage());
            return false;
        }
    }

    public Boolean cancelHoadon(int idHoadon, String username){
        try {
            HoaDon_Entity hoaDon_entity = hoaDon_repository.findByIdHoaDon(idHoadon);
            if(hoaDon_entity.getCustomerEntity().getUserEntity().getUsername().equals(username) &&
                    hoaDon_entity.getTinhTrang().equals(HoaDon_Entity.eTinhTrang.thanhCong)){

                // check time cancel
                Date timeN = new Date();
                String strDate = hoaDon_entity.getLichEntity().getNgay().toString();
                String strTime = hoaDon_entity.getLichEntity().getGio().toString();
                Date TimeC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate +" "+strTime);
                long longDay = (86400000/4); // hủy trước 6h
                if ((TimeC.getTime() - timeN.getTime()) < longDay){
                    return  false;
                }

                //check point
                if(hoaDon_entity.getUsedPoint() > 0){
                    return  false;
                }

                hoaDon_entity.setTinhTrang(HoaDon_Entity.eTinhTrang.Huy);
                hoaDon_repository.save(hoaDon_entity);

                Customer_Entity customer =  hoaDon_entity.getCustomerEntity();
                int point =  customer.getPromoPoint() - 1000;
                customer.setPromoPoint(point);
                customer_repository.save(customer);
                return  true;
            }
            return  false;
        }catch (Exception e){
            System.out.println("Exception "+e);
            return false;
        }
    }


    public boolean checkghetrung (Lich_Entity lich,List<CtHoaDon_DTO> listCT){
        List<HoaDon_Entity> list = hoaDon_repository.findByLichEntity(lich);
        for (CtHoaDon_DTO ctHoaDon_dto : listCT){
            for (HoaDon_Entity hoaDon_entity : list ){
                for(CtHoaDon_Entity ctHoaDon_entity : hoaDon_entity.getCtHoaDon_Entity()){
                    if(ctHoaDon_dto.getCot() == ctHoaDon_entity.getCot() && ctHoaDon_dto.getHang() == ctHoaDon_entity.getHang()){
                        return  false;
                    }
                }
            }
        }
        return  true;
    }

//    Admin

    public Object getHoaDonFiltered(int pageNum, int size, Optional<Integer> buyEr, String buyerType, String direction, String orderBy) {
        try {
            Sort sort;
            if (direction.toUpperCase().equals("ASC"))
                sort = new Sort(Sort.Direction.ASC, orderBy);
            else sort = new Sort(Sort.Direction.DESC, orderBy);

            Pageable pageable = new PageRequest(pageNum, size, sort);

            Page<HoaDon_Entity> filteredHoaDon;

            if (buyerType.equals("customer")) {//Get HoaDon created by Customer
                if (buyEr.isPresent()) { //Followed by Customer ID
                    Customer_Entity customer_entity = customer_repository.findOne(buyEr.get());
                    filteredHoaDon = hoaDon_repository.findAllByCustomerEntity(customer_entity, pageable);
                } else //All HoaDon
                    filteredHoaDon = hoaDon_repository.findAllByCustomerEntityIsNotNull(pageable);

            } else {//Get HoaDon created by Admin
                if (buyEr.isPresent()) //Followed by admin ID
                    filteredHoaDon = hoaDon_repository.findAllByIdUser(buyEr.get(), pageable);
                else //All HoaDon
                    filteredHoaDon = hoaDon_repository.findAllByIdUserIsNotNull(pageable);
            }

            HoaDon_Filtered result = new HoaDon_Filtered(filteredHoaDon);

            return result;
        } catch (Exception e) {
            System.out.println("Error in HoaDon_Service - getHoaDonFiltered Function: " + e);
            return "Failed";
        }
    }

    public Object getHoaDon(int idHoaDon) {
        try {

            return hoaDon_repository.getOne(idHoaDon);
        } catch (Exception e) {
            System.out.println("Error in HoaDon_Service - getHoaDon Function: " + e);
            return "Failed";
        }
    }

    public boolean createHoaDon(HoaDon_DTO hoaDon_dto) {
        try {
            hoaDon_dto.setCreatedAt(new Date());
            hoaDon_repository.save(mapper.map(hoaDon_dto, HoaDon_Entity.class));
            return true;
        } catch (Exception e) {
            System.out.println("Error in HoaDon_Service - createHoaDon Function: " + e);
            return false;
        }
    }

    public boolean updateHoaDon(HoaDon_DTO hoaDon_dto) {
        try {
            if (hoaDon_repository.exists(hoaDon_dto.getIdHoaDon())) {
                hoaDon_repository.save(mapper.map(hoaDon_dto, HoaDon_Entity.class));
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in HoaDon_Service - updateHoaDon Function: " + e);
            return false;
        }
    }

    public boolean deleteHoaDon(int idHoaDon) {
        try {
            if (hoaDon_repository.exists(idHoaDon)) {
                hoaDon_repository.delete(idHoaDon);
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in HoaDon_Service - deleteHoaDon Function: " + e);
            return false;
        }
    }

}