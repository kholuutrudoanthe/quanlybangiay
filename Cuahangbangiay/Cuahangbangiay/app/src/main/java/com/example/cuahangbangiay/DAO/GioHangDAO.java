package com.example.cuahangbangiay.DAO;

import static com.example.cuahangbangiay.GUI.MainActivityLogIn.SQLite_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cuahangbangiay.DTO.GioHang;

import java.util.ArrayList;
import java.util.List;

public class GioHangDAO {

    public static Boolean them_giohang(GioHang GioHang) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", GioHang.getEMAIL());
        contentValues.put("MASP", GioHang.getMASP());
        contentValues.put("sl", GioHang.getSl());
        contentValues.put("tongtien", GioHang.getTongtien());
        contentValues.put("trangthai", GioHang.getTrangthai());
        long result = MyDB.insert("giohang", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<GioHang> getall_giohang() {
        List<GioHang> giohang_s = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From giohang where trangthai = ?", new String[]{String.valueOf(0)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                GioHang GioHang = new GioHang();
                GioHang.setIdgh(cursor.getInt(0));
                GioHang.setEMAIL(cursor.getString(1));
                GioHang.setMASP(cursor.getString(2));
                GioHang.setSl(cursor.getInt(3));
                GioHang.setTongtien(cursor.getInt(4));
                GioHang.setTrangthai(cursor.getInt(5));
                giohang_s.add(GioHang);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return giohang_s;
    }
    public static GioHang get_giohang(int idgh) {
        GioHang GioHang = new GioHang();
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from giohang where idgh = ?", new String[]{String.valueOf(idgh)});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            GioHang.setIdgh(cursor.getInt(0));
            GioHang.setEMAIL(cursor.getString(1));
            GioHang.setMASP(cursor.getString(2));
            GioHang.setSl(cursor.getInt(3));
            GioHang.setTongtien(cursor.getInt(4));
            GioHang.setTrangthai(cursor.getInt(5));
            cursor.close();
            return GioHang;
        }
        return null;
    }
    public static boolean Sua_giohang(int idgh, int sl, int tongtien) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sl",sl);
        contentValues.put("tongtien", tongtien);
        long result = MyDB.update("giohang",contentValues,"idgh=?" , new String[]{String.valueOf(idgh)});
        if (result == -1)
            return false;
        else
            return true;
    }
    public static boolean Sua_trangthai(int idgh, int sl) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("trangthai",sl);
        long result = MyDB.update("giohang",contentValues,"idgh=?" , new String[]{String.valueOf(idgh)});
        if (result == -1)
            return false;
        else
            return true;
    }
    public static boolean xoa_giohang(int idgh) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        long result = MyDB.delete("giohang","idgh=?" , new String[]{String.valueOf(idgh)});
        if (result == -1)
            return false;
        else
            return true;
    }
}
