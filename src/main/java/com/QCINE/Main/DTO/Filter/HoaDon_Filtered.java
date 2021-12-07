package com.QCINE.Main.DTO.Filter;


import com.QCINE.Main.DTO.HoaDon_DTO;
import com.QCINE.Main.Entity.HoaDon_Entity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class HoaDon_Filtered {
    @Autowired
    ModelMapper mapper;

    private int currentPage;
    private int totalPage;
    private List<HoaDon_DTO> filteredHoaDon;

    public HoaDon_Filtered(Page<HoaDon_Entity> hoaDon_entities) {
        this.currentPage = hoaDon_entities.getNumber();
        this.totalPage = hoaDon_entities.getTotalPages();
        List<HoaDon_DTO> temp = new ArrayList<>();
        for(HoaDon_Entity hoaDon_entity: hoaDon_entities.getContent()){
            temp.add(mapper.map(hoaDon_entity, HoaDon_DTO.class));
        }
        this.filteredHoaDon = temp;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<HoaDon_DTO> getFilteredHoaDon() {
        return filteredHoaDon;
    }

    public void setFilteredHoaDon(List<HoaDon_DTO> filteredHoaDon) {
        this.filteredHoaDon = filteredHoaDon;
    }
}
