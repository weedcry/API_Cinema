package com.QCINE.Main.DTO.Filter;

import com.QCINE.Main.DTO.Lich_DTO;
import com.QCINE.Main.Entity.Lich_Entity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class Lich_Filtered {
    @Autowired
    ModelMapper mapper;

    private int currentPage;
    private int totalPage;
    private List<Lich_DTO> filteredLich;



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

    public List<Lich_DTO> getFilteredLich() {
        return filteredLich;
    }

    public void setFilteredLich(List<Lich_DTO> filteredLich) {
        this.filteredLich = filteredLich;
    }
}