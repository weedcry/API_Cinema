package com.QCINE.Main.DTO.Filter;

import com.QCINE.Main.DTO.Customer_DTO;

import java.util.List;

public class Customer_Filtered {
    private int currentPage;
    private int totalPage;
    private List<Customer_DTO> filteredCustomer;

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

    public List<Customer_DTO> getFilteredCustomer() {
        return filteredCustomer;
    }

    public void setFilteredCustomer(List<Customer_DTO> filteredCustomer) {
        this.filteredCustomer = filteredCustomer;
    }
}