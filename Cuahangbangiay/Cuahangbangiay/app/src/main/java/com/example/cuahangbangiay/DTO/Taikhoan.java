package com.example.cuahangbangiay.DTO;

public class Taikhoan {
    private String email;
    private String hoten;
    private String mk;
    private String sdt;
    private String diachi;

    public Taikhoan() {
    }

    public Taikhoan(String email, String hoten, String mk, String sdt, String diachi) {
        this.email = email;
        this.hoten = hoten;
        this.mk = mk;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
