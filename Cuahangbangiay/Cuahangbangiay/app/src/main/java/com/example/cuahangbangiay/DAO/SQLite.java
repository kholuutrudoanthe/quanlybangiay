package com.example.cuahangbangiay.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {
    public SQLite(@Nullable Context context) {
        super(context, "Quanlybangiay", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS TaiKhoan(EMAIL TEXT PRIMARY KEY, HOTEN TEXT, MATKHAU TEXT,SDT TEXT,DIACHI TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS SanPham(MASP TEXT PRIMARY KEY, TENSP TEXT, SOLUONG DOUBLE, GIATIEN INTEGER, HINHANH BLOB)");

        db.execSQL("create Table IF NOT EXISTS giohang(idgh INTEGER PRIMARY KEY AUTOINCREMENT,EMAIL TEXT, MASP TEXT,sl INTEGER,tongtien INTEGER,trangthai INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS HoaDonNhap(MAHDN TEXT PRIMARY KEY, MASP TEXT, TENSP TEXT,TENNV TEXT, SOLUONG DOUBLE, GIATIEN INTEGER, TONGTIEN INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS HoaDonXuat(iddh INTEGER PRIMARY KEY AUTOINCREMENT, idgh INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
