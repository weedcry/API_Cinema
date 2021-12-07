package com.QCINE.Main.DTO.Filter;

import com.QCINE.Main.DTO.Phim_DTO;

import java.util.List;

public class Phim_Filtered {
    private int currentPage;
    private int totalPage;
    private List<Phim_DTO> resultPhim;

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

    public List<Phim_DTO> getResultPhim() {
        return resultPhim;
    }

    public void setResultPhim(List<Phim_DTO> resultPhim) {
        this.resultPhim = resultPhim;
    }
}
