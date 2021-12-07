package com.QCINE.Main.DTO.thongke;

import java.util.List;

public class OutputKVThongKe_DTO {

    private String tenRap;

    private List<OutputThongKe_DTO> list;

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public List<OutputThongKe_DTO> getList() {
        return list;
    }

    public void setList(List<OutputThongKe_DTO> list) {
        this.list = list;
    }
}
