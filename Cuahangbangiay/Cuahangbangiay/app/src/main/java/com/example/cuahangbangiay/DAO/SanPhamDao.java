package com.example.cuahangbangiay.DAO;

import static com.example.cuahangbangiay.GUI.MainActivityLogIn.SQLite_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cuahangbangiay.DTO.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {
    public static Boolean them_sanpham(SanPham sanpham) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MASP", sanpham.getMASP());
        contentValues.put("TENSP", sanpham.getTENSP());
        contentValues.put("SOLUONG", sanpham.getSOLUONG());
        contentValues.put("GIATIEN", sanpham.getGIATIEN());
        contentValues.put("HINHANH", sanpham.getHINHANH());
        long result = MyDB.insert("SanPham", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<SanPham> getall_sanpham() {
        List<SanPham> sanphams = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From SanPham", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                SanPham sanpham = new SanPham();
                sanpham.setMASP(cursor.getString(0));
                sanpham.setTENSP(cursor.getString(1));
                sanpham.setSOLUONG(cursor.getInt(2));
                sanpham.setGIATIEN(cursor.getInt(3));
                sanpham.setHINHANH(cursor.getBlob(4));
                sanphams.add(sanpham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return sanphams;
    }

    public static List<SanPham> Tk_sp(String tensp) {
        List<SanPham> sanphams = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From SanPham where TENSP LIKE ?", new String[]{"%" + tensp + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                SanPham sanpham = new SanPham();
                sanpham.setMASP(cursor.getString(0));
                sanpham.setTENSP(cursor.getString(1));
                sanpham.setSOLUONG(cursor.getInt(2));
                sanpham.setGIATIEN(cursor.getInt(3));
                sanpham.setHINHANH(cursor.getBlob(4));
                sanphams.add(sanpham);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return sanphams;
    }


    public static SanPham get_sp(String idsp) {
        SanPham sanpham = new SanPham();
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from SanPham where MASP = ?", new String[]{idsp});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            sanpham.setMASP(cursor.getString(0));
            sanpham.setTENSP(cursor.getString(1));
            sanpham.setSOLUONG(cursor.getInt(2));
            sanpham.setGIATIEN(cursor.getInt(3));
            sanpham.setHINHANH(cursor.getBlob(4));
            cursor.close();
            return sanpham;
        }
        return null;
    }
    public static boolean Sua_sanpham(SanPham sanpham) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TENSP", sanpham.getTENSP());
        contentValues.put("SOLUONG", sanpham.getSOLUONG());
        contentValues.put("GIATIEN", sanpham.getGIATIEN());
        contentValues.put("HINHANH", sanpham.getHINHANH());
        long result = MyDB.update("SanPham",contentValues,"MASP=?" , new String[]{sanpham.getMASP()});
        if (result == -1)
            return false;
        else
            return true;
    }
    public static boolean Sua_sl(String masp,int soluong) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("SOLUONG", soluong);
        long result = MyDB.update("SanPham",contentValues,"MASP=?" , new String[]{masp});
        if (result == -1)
            return false;
        else
            return true;
    }
    public static boolean xoa_sp(String idsp) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        long result = MyDB.delete("SanPham","MASP=?" , new String[]{String.valueOf(idsp)});
        if (result == -1)
            return false;
        else
            return true;
    }
}
