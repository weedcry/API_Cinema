package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Filter.Login_info_DTO;
import com.QCINE.Main.DTO.Filter.User_Filtered;
import com.QCINE.Main.DTO.User_DTO;
import com.QCINE.Main.Entity.Role_Entity;
import com.QCINE.Main.Entity.User_Entity;
import com.QCINE.Main.Repository.Role_Repository;
import com.QCINE.Main.Repository.User_Repository;
import com.QCINE.Main.payload.request.LoginRequest;
import com.QCINE.Main.security.service.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class User_Service implements UserDetailsService {
    @Autowired
    User_Repository userRepository;

    @Autowired
    Role_Repository roleRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User_Entity user = userRepository.findByUsername(s).
                    orElseThrow(() -> new UsernameNotFoundException("username not found with :"+s));
            return UserDetailsImpl.build(user);
    }



//    Admin

    public Object getAllUser() {
        try {
            List<User_Entity> user_entities = userRepository.findAll();
            List<User_DTO> result = new ArrayList<>();
            if (!user_entities.isEmpty()) {
                for (User_Entity user : user_entities) {
                    result.add(mapper.map(user, User_DTO.class));
                }
                return result;
            } else return "Failed";
        } catch (Exception e) {
            System.out.println("Error in User_Service - getAllUser Function: " + e);
            return "Failed";
        }
    }

    public Object getAllFiltered(String name, int pageNum, int size, String orderBy, String direction, Optional<Integer> idRole) {
        try {
            Page<User_Entity> user_entities;
            User_Filtered result = new User_Filtered();
            Sort sort;

            if (direction.toUpperCase().equals("ASC"))
                sort = new Sort(Sort.Direction.ASC, orderBy);
            else sort = new Sort(Sort.Direction.DESC, orderBy);

            Pageable page = new PageRequest(pageNum, size, sort);

            if (idRole.isPresent()) {
                Role_Entity role = roleRepository.findOne(idRole.get());
//                user_entities = userRepository.findAllByRoleEntityAndNameContaining(role, name, page);
                user_entities = userRepository.findAll(page);
            } else {
                user_entities = userRepository.findAllByNameContaining(name, page);
            }

            List<User_DTO> user_dtos = new ArrayList<>();
            for (User_Entity user : user_entities.getContent()) {
                user_dtos.add(mapper.map(user, User_DTO.class));
            }
            result.setCurrentPage(user_entities.getNumber() + 1);
            result.setResultUser(user_dtos);
            result.setTotalPage(user_entities.getTotalPages());
            return result;
        } catch (Exception e) {
            System.out.println("Error in User_Service - getAllFiltered Function: " + e);
            return "Failed";
        }
    }

    public Object getUser(int id) {
        try {
            User_DTO result = mapper.map(userRepository.getOne(id), User_DTO.class);
            return result;
        } catch (Exception e) {
            System.out.println("Error in User_Service - getUser Function: " + e);
            return "Failed";
        }
    }

    public Object getUserLogin(LoginRequest login_dto) {
        try {
            Optional<User_Entity> user_entity = userRepository.findByUsernameAndPassword(login_dto.getUsername(), login_dto.getPassword());
            if (user_entity.isPresent()) {
//                String role = String.valueOf(user_entity.get().getRoleEntity().getRoleName());
                Login_info_DTO result = new Login_info_DTO();
                result.setName(user_entity.get().getName());
//                result.setRole(role);
                result.setAuth("This is auth token");
                result.setIdUser(user_entity.get().getIdUser());
                result.setIdRap(user_entity.get().getIdRap());
                return result;
            } else return "failed";

        } catch (Exception e) {
            System.out.println("Error in User_Service - getUserLogin Function: " + e);
            return "Failed";
        }
    }

    public boolean createUser(User_DTO user_dto) {
        try {
            userRepository.save(mapper.map(user_dto, User_Entity.class));
            return true;
        } catch (Exception e) {
            System.out.println("Error in User_Service - createUser Function: " + e);
            return false;
        }
    }

    public boolean updateUser(User_DTO user_dto) {
        try {
            if (userRepository.exists(user_dto.getIdUser())) {
                userRepository.save(mapper.map(user_dto, User_Entity.class));
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in User_Service - updateUser Function: " + e);
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try {
            if (userRepository.exists(id)) {
                userRepository.delete(id);
                return true;
            } else return false;
        } catch (Exception e) {
            System.out.println("Error in User_Service - deleteUser Function: " + e);
            return false;
        }
    }


}

