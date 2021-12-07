package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Role_DTO;
import com.QCINE.Main.Entity.Role_Entity;
import com.QCINE.Main.Repository.Role_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Role_Service {
    @Autowired
    ModelMapper mapper;

    @Autowired
    Role_Repository roleRepository;

    public Object getAllRole(){
        try{
            List<Role_Entity> role_entities = roleRepository.findAll();
            List<Role_DTO> result = new ArrayList<>();
            if (!role_entities.isEmpty()){
                for(Role_Entity role: role_entities){
                    result.add(mapper.map(role, Role_DTO.class));
                }
                return result;
            } else return "Failed";
        } catch (Exception e){
            System.out.println("Error in Role_Service - getAllRole Function: " + e);
            return "Failed";
        }
    }

    public Object getRole(int idRole){
        try{
            Role_DTO result = mapper.map(roleRepository.getOne(idRole), Role_DTO.class);
            return result;
        } catch (Exception e){
            System.out.println("Error in Role_Service - getRole Function: " + e);
            return "Failed";
        }
    }

    public boolean createRole(Role_DTO role_dto){
        try{
            roleRepository.save(mapper.map(role_dto, Role_Entity.class));
            return true;
        } catch (Exception e){
            System.out.println("Error in Role_Service - createRole Function: " + e);
            return false;
        }
    }

    public boolean updateRole(Role_DTO role_dto){
        try{
            if (roleRepository.exists(role_dto.getIdRole())){
                roleRepository.save(mapper.map(role_dto, Role_Entity.class));
                return true;
            } else return false;
        } catch (Exception e){
            System.out.println("Error in Role_Service - updateRole Function: " + e);
            return false;
        }
    }

    public boolean deleteRole(int idRole){
        try{
            if (roleRepository.exists(idRole)){
                roleRepository.delete(idRole);
                return true;
            } else return false;
        } catch (Exception e){
            System.out.println("Error in Role_Service - deleteRole Function: " + e);
            return false;
        }
    }
}