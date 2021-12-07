package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Week_DTO;
import com.QCINE.Main.Entity.Week_Entity;
import com.QCINE.Main.Repository.Week_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Week_Service {
    @Autowired
    ModelMapper mapper;

    @Autowired
    Week_Repository week_repository;

    public Object getAllDay() {

        List<Week_DTO> week_dtos = new ArrayList<>();
        try {
            List<Week_Entity> week_entities = week_repository.findAll();
            if (!week_entities.isEmpty()) {
                for (Week_Entity temp : week_entities) {
                    week_dtos.add(mapper.map(temp, Week_DTO.class));
                }
            }
        } catch (Exception e) {
            System.out.println("Error in Week_Service - getAllDay function: " + e.getStackTrace());
        }
        if (week_dtos.isEmpty())
            return null;
        else return week_dtos;
    }

    public Object getDay(String day) {
        Week_DTO result = new Week_DTO();
        try {
            Week_Entity week_entity = week_repository.getOne(day);
            if (week_entity!= null)
                result = mapper.map(week_entity, Week_DTO.class);

        } catch (Exception e) {
            System.out.println("Error in Week_Service - getDay func: " + e.getStackTrace());
        }
        if (result!= null)
            return result;
        else return false;
    }

    public boolean createDay(Week_DTO week_dto){
        boolean ans = false;
        try{
            if (!week_repository.exists(week_dto.getDayOfWeek())){
                week_repository.save(mapper.map(week_dto, Week_Entity.class));
                ans = true;
            }

        } catch(Exception e){
            System.out.println("Error in Week_Service - createDay func: "+ e.getStackTrace());
        }
        return  ans;
    }

    public boolean updateDay(Week_DTO week_dto){
        boolean ans = false;
        try{
            {
                if (week_repository.exists(week_dto.getDayOfWeek())){

                    week_repository.save(mapper.map(week_dto, Week_Entity.class));
                    ans = true;
                }
            }
        } catch(Exception e){
            System.out.println("Error in Week_Service - updateDay func: "+ e.getStackTrace());
        }
        return ans;
    }

    public boolean deleteDay(String day){
        boolean ans = false;
        try{
            if (week_repository.exists(day)){
                week_repository.delete(day);
                ans = true;
            }
        }catch (Exception e){
            System.out.println("Error in Week_Service - deleteDay func: "+ e.getStackTrace());
        }
        return ans;
    }
}