package com.QCINE.Main.Service;

import com.QCINE.Main.DTO.Filter.Holidays_Filtered;
import com.QCINE.Main.DTO.Holidays_DTO;
import com.QCINE.Main.Entity.Holidays_Entity;
import com.QCINE.Main.Repository.Holidays_Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Holidays_Service {
    @Autowired
    ModelMapper mapper;

    @Autowired
    Holidays_Repository holidays_repository ;

    public Object getAllHolidays(int pageNum, int size) {
        Holidays_Filtered result = new Holidays_Filtered();
        try {
            Sort sort = new Sort(Sort.Direction.ASC, "startDate");
            Pageable pageable = new PageRequest(pageNum, size, sort);
            Page<Holidays_Entity> pageFiltered = holidays_repository.findAll(pageable);

            List<Holidays_Entity> holidays_entities = pageFiltered.getContent();
            List<Holidays_DTO> holidays_dtos = new ArrayList<>();
            if (!holidays_entities.isEmpty()) {
                for (Holidays_Entity temp : holidays_entities) {
                    holidays_dtos.add(mapper.map(temp, Holidays_DTO.class));
                }
            }
            result.setCurrentPage(pageFiltered.getNumber());
            result.setTotalPage(pageFiltered.getTotalPages());
            result.setResultPage(holidays_dtos);
        } catch (Exception e) {
            System.out.println("Error in Holidays_Service - getAllHolidays func: " + e.getStackTrace());
        }
        return result;
    }

    public Object getHoliday(String id) {
        Holidays_DTO result = new Holidays_DTO();
        try {
            Holidays_Entity holidays_entity = holidays_repository.findOne(id);
            result = mapper.map(holidays_entity, Holidays_DTO.class);
        } catch (Exception e) {
            System.out.println("Error in Holidays_Service - getHoliday: " + e.getStackTrace());
        }
        return result;
    }

    public float getHolidayPrice(Date date){
        float price =0;

        try {
            List<Holidays_Entity> holidays_entities = holidays_repository.findAllByStartDateLessThanEqualAndEndDateIsGreaterThanEqual(date, date);
            if (!holidays_entities.isEmpty()){
                price = holidays_entities.get(0).getGia();
            }
        } catch (Exception e){
            System.out.println("Error in Holidays_Service - getHolidayPrice: "+e.getStackTrace());
        }

        return price;
    }

    public boolean createHoliday(Holidays_DTO holidays_dto) {
        boolean ans = false;

        try {
            if (!holidays_repository.exists(holidays_dto.getIdHoliday())) {
                holidays_repository.save(mapper.map(holidays_dto, Holidays_Entity.class));
                ans = true;
            }
        } catch (Exception e) {
            System.out.println("Error in Holidays_Service - createHolidays: " + e.getStackTrace());
        }

        return ans;
    }

    public boolean updateHoliday(Holidays_DTO holidays_dto) {
        boolean ans = false;

        try {
            if (holidays_repository.exists(holidays_dto.getIdHoliday())) {
                holidays_repository.save(mapper.map(holidays_dto, Holidays_Entity.class));
                ans = true;
            }
        } catch (Exception e) {
            System.out.println("Error in Holidays_Service - updateHolidays: " + e.getStackTrace());
        }

        return ans;
    }

    public boolean deleteHoliday(String id) {
        boolean ans = false;

        try {
            if (holidays_repository.exists(id)) {

                holidays_repository.delete(id);
                ans = true;
            }
        } catch (Exception e) {
            System.out.println("Error in Holidays_Service - deleteHoliday: " + e.getStackTrace());
        }

        return ans;
    }
}