package com.QCINE.Main.DTO.Filter;

import com.QCINE.Main.DTO.Holidays_DTO;

import java.util.List;

public class Holidays_Filtered {
    private int currentPage;
    private int totalPage;
    private List<Holidays_DTO> resultPage;

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

    public List<Holidays_DTO> getResultPage() {
        return resultPage;
    }

    public void setResultPage(List<Holidays_DTO> resultPage) {
        this.resultPage = resultPage;
    }
}