package com.example.cuahangbangiay.DTO;

public class HoaDonBan {
    int iddh;
    int idgh;

    public HoaDonBan() {
    }

    public HoaDonBan(int iddh, int idgh) {
        this.iddh = iddh;
        this.idgh = idgh;
    }

    public int getIddh() {
        return iddh;
    }

    public void setIddh(int iddh) {
        this.iddh = iddh;
    }

    public int getIdgh() {
        return idgh;
    }

    public void setIdgh(int idgh) {
        this.idgh = idgh;
    }
}
