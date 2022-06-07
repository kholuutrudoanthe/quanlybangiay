package com.example.cuahangbangiay.DTO;

public class GioHang {
    int idgh;
    String EMAIL;
    String MASP;
    int sl;
    int tongtien;
    int trangthai;

    public GioHang() {
    }

    public GioHang(int idgh, String EMAIL, String MASP, int sl, int tongtien, int trangthai) {
        this.idgh = idgh;
        this.EMAIL = EMAIL;
        this.MASP = MASP;
        this.sl = sl;
        this.tongtien = tongtien;
        this.trangthai = trangthai;
    }

    public int getIdgh() {
        return idgh;
    }

    public void setIdgh(int idgh) {
        this.idgh = idgh;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
