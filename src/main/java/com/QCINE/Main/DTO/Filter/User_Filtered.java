package com.QCINE.Main.DTO.Filter;

import com.QCINE.Main.DTO.User_DTO;

import java.util.List;

public class User_Filtered {

    private int currentPage;
    private int totalPage;
    private List<User_DTO> resultUser;

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

    public List<User_DTO> getResultUser() {
        return resultUser;
    }

    public void setResultUser(List<User_DTO> resultUser) {
        this.resultUser = resultUser;
    }
}