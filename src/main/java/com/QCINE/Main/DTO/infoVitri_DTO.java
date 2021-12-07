package com.QCINE.Main.DTO;

import javax.validation.constraints.NotNull;

public class infoVitri_DTO {

    @NotNull
    private int Hang;

    @NotNull
    private int Cot;

    public int getHang() {
        return Hang;
    }

    public void setHang(int hang) {
        Hang = hang;
    }

    public int getCot() {
        return Cot;
    }

    public void setCot(int cot) {
        Cot = cot;
    }
}
