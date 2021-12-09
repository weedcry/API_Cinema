package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Customer_DTO;
import com.QCINE.Main.DTO.Filter.Customer_Filtered;
import com.QCINE.Main.Entity.Customer_Entity;
import com.QCINE.Main.Entity.User_Entity;
import com.QCINE.Main.Repository.Customer_Repository;
import com.QCINE.Main.Repository.User_Repository;
import com.QCINE.Main.payload.request.SignupRequest;
import com.QCINE.Main.payload.response.MessageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class Customer_Service {

    @Autowired
    Customer_Repository customer_repository;

    @Autowired
    User_Repository user_repository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ModelMapper mapper;



    public Customer_Entity findCustomerEntityByUsername(String username){
        Customer_Entity customer_entity = new Customer_Entity();
        try {
             customer_entity = customer_repository.findByUserEntity(user_repository.findByUsername(username).get());
            return  customer_entity;
        }catch (Exception e){
            System.out.println("Exception "+e);
            return  customer_entity;
        }
    }

    public Customer_DTO findCustomerDTO(String username){
        Customer_DTO customer_DTO = new Customer_DTO();
        try {
            Customer_Entity customer_entity = findCustomerEntityByUsername(username);
                    customer_DTO = mapper.map(customer_entity,Customer_DTO.class);
                    customer_DTO.setEmail(customer_entity.getUserEntity().getEmail());
            return  customer_DTO;
        }catch (Exception e){
            System.out.println("Exception "+e);
            return  customer_DTO;
        }
    }

    public Boolean createCustomer(SignupRequest signRes, User_Entity user){
        // tạo customers
        try {
            String linkphotodefault = "https://s3.us-east-2.amazonaws.com/myawsbucketappfile/1622470096048-avatar.png";
            Customer_Entity customersEntity = new Customer_Entity();
            customersEntity.setAvatar(linkphotodefault);
            customersEntity.setFirstName(signRes.getFirstname());
            customersEntity.setLastName(signRes.getLastname());
            customersEntity.setPhoneNumber(signRes.getPhone());
            customersEntity.setPromoPoint(0);
            customersEntity.setUserEntity(user);
            customer_repository.save(customersEntity);
            return true;
        }catch (Exception e){

            System.out.println("Exception "+e);
            return false;
        }

    }

    public Boolean updateCustomer(Customer_DTO customer_dto,String username){
        // update customers
        try {

            Customer_Entity customersEntity = findCustomerEntityByUsername(username);
            if(customersEntity == null){
                return  false;
            }

            if(!customersEntity.getUserEntity().getEmail().equals(customer_dto.getEmail())){
                if (user_repository.existsByEmail(customer_dto.getEmail())) {
                    return  false;
                }
                User_Entity user = customersEntity.getUserEntity();
                user.setEmail(customer_dto.getEmail());
                user_repository.save(user);
            }

            customersEntity.setFirstName(customer_dto.getFirstName());
            customersEntity.setLastName(customer_dto.getLastName());
            customersEntity.setPhoneNumber(customer_dto.getPhoneNumber());
            customer_repository.save(customersEntity);
            return true;
        }catch (Exception e){

            System.out.println("Exception "+e);
            return false;
        }
    }

    public int checkpassword(String username,String pass){
        boolean result = false;
        try {
            User_Entity user = user_repository.findByUsername(username).get();
            result = encoder.matches(pass,user.getPassword());
        }catch (Exception e){
            System.out.println("Exception: "+e);
            return 0;
        }
        if(result){
            return 1;
        }
        return 0;
    }



    public Object changepassword(String username,String newpass){
        try {
            User_Entity user = user_repository.findByUsername(username).get();
            user.setPassword(encoder.encode(newpass));
            user_repository.save(user);
        }catch (Exception e){
            MessageResponse mes = new MessageResponse("error");
            return mes;
        }
        MessageResponse mes = new MessageResponse("success");
        return mes;
    }


//    Admin

    public Object getAllCustomer(int pageNum, int size, String direction, String orderBy) {
        try {
            Page<Customer_Entity> customer_entities;

            Sort sort;
            if (direction.toUpperCase().equals("ASC"))
                sort = new Sort(Sort.Direction.ASC, orderBy);
            else sort = new Sort(Sort.Direction.DESC, orderBy);

            Pageable pageable = new PageRequest(pageNum, size, sort);

            customer_entities = customer_repository.findAll(pageable);
            List<Customer_DTO> customer_dtos = new ArrayList<>();
            for (Customer_Entity customer_entity : customer_entities.getContent()) {
                customer_dtos.add(mapper.map(customer_entity, Customer_DTO.class));
            }

            Customer_Filtered result = new Customer_Filtered();
            result.setCurrentPage(customer_entities.getNumber()+1);
            result.setTotalPage(customer_entities.getTotalPages());
            result.setFilteredCustomer(customer_dtos);

            return result;
        } catch (Exception e) {
            System.out.println("Error in Customer_Service - getAllCustomer Function: " + e);
            return "Failed";
        }
    }

    public Object getCustomer(int idCustomer) {
        try {
            return mapper.map(customer_repository.findOne(idCustomer), Customer_DTO.class);
        } catch (Exception e) {
            System.out.println("Error in Customer_Service - getCustomer Function: " + e);
            return "Failed";
        }
    }

    public boolean createCustomer(Customer_DTO customer_dto) {
        try {
            customer_dto.setIdCustomer(1000000000);
            customer_repository.save(mapper.map(customer_dto, Customer_Entity.class));
            return true;
        } catch (Exception e) {
            System.out.println("Error in Customer_Service - createCustomer Function: " + e);
            return false;
        }
    }

    public boolean updateCustomer(Customer_DTO customer_dto) {
        try {
            if (customer_repository.exists(customer_dto.getIdCustomer())) {
                customer_repository.save(mapper.map(customer_dto, Customer_Entity.class));
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in Customer_Service - updateCustomer Function: " + e);
            return false;
        }
    }

    public boolean deleteCustomer(int idCustomer) {
        try {
            if (customer_repository.exists(idCustomer)) {
                customer_repository.delete(idCustomer);
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in Customer_Service - deleteCustomer Function: " + e);
            return false;
        }
    }
}
