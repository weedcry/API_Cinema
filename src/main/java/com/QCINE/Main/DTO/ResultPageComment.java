package com.QCINE.Main.DTO;

import java.util.ArrayList;
import java.util.List;

public class ResultPageComment {
    private int page;
    private int totalpage;
    private List<Comment_DTO> listResult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public List<Comment_DTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<Comment_DTO> listResult) {
        this.listResult = listResult;
    }
}
