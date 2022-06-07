package com.example.cuahangbangiay.DTO;

public class SanPham {
    String MASP,TENSP;
    int GIATIEN,SOLUONG;
    byte[]HINHANH;

    public SanPham() {
    }

    public SanPham(String MASP, String TENSP, int GIATIEN, int SOLUONG, byte[] HINHANH) {
        this.MASP = MASP;
        this.TENSP = TENSP;
        this.GIATIEN = GIATIEN;
        this.SOLUONG = SOLUONG;
        this.HINHANH = HINHANH;
    }

    public String getMASP() {
        return MASP;
    }

    public void setMASP(String MASP) {
        this.MASP = MASP;
    }

    public String getTENSP() {
        return TENSP;
    }

    public void setTENSP(String TENSP) {
        this.TENSP = TENSP;
    }

    public int getGIATIEN() {
        return GIATIEN;
    }

    public void setGIATIEN(int GIATIEN) {
        this.GIATIEN = GIATIEN;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public byte[]
    getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(byte[] HINHANH) {
        this.HINHANH = HINHANH;
    }

    @Override
    public String toString() {
        return getMASP();
    }
}
