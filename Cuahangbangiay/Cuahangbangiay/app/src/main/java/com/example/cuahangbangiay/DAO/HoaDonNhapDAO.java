package com.example.cuahangbangiay.DAO;


import static com.example.cuahangbangiay.GUI.MainActivityLogIn.SQLite_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cuahangbangiay.DTO.HoaDonNhap;

import java.util.ArrayList;
import java.util.List;

public class HoaDonNhapDAO {
//            db.execSQL("CREATE TABLE IF NOT EXISTS HoaDonNhap(MAHDN TEXT PRIMARY KEY,
//            MASP TEXT, TENSP TEXT,TENNV TEXT, SOLUONG DOUBLE, GIATIEN INTEGER, TONGTIEN INTEGER)");

    public static Boolean Them_HDN(HoaDonNhap HoaDonNhap) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MAHDN", HoaDonNhap.getMahdn());
        contentValues.put("MASP", HoaDonNhap.getMasp());
        contentValues.put("TENSP", HoaDonNhap.getTensp());
        contentValues.put("TENNV", HoaDonNhap.getTennv());
        contentValues.put("SOLUONG", HoaDonNhap.getSoluong());
        contentValues.put("GIATIEN", HoaDonNhap.getDongia());
        contentValues.put("TONGTIEN", HoaDonNhap.getTongtien());
        long result = MyDB.insert("HoaDonNhap", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<HoaDonNhap> DS_HDN() {
        List<HoaDonNhap> HoaDonNhaps = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From HoaDonNhap", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                HoaDonNhap HoaDonNhap = new HoaDonNhap();
                HoaDonNhap.setMahdn(cursor.getString(0));
                HoaDonNhap.setMasp(cursor.getString(1));
                HoaDonNhap.setTensp(cursor.getString(2));
                HoaDonNhap.setTennv(cursor.getString(3));
                HoaDonNhap.setSoluong(cursor.getInt(4));
                HoaDonNhap.setDongia(cursor.getInt(5));
                HoaDonNhap.setTongtien(cursor.getInt(6));
                HoaDonNhaps.add(HoaDonNhap);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return HoaDonNhaps;
    }

    public static HoaDonNhap TT_HoaDonNhap(String MAHDN) {
        HoaDonNhap HoaDonNhap = new HoaDonNhap();
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from HoaDonNhap where MAHDN = ?", new String[]{MAHDN});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            HoaDonNhap.setMahdn(cursor.getString(0));
            HoaDonNhap.setMasp(cursor.getString(1));
            HoaDonNhap.setTensp(cursor.getString(2));
            HoaDonNhap.setTennv(cursor.getString(3));
            HoaDonNhap.setSoluong(cursor.getInt(4));
            HoaDonNhap.setDongia(cursor.getInt(5));
            HoaDonNhap.setTongtien(cursor.getInt(6));
            cursor.close();
            return HoaDonNhap;
        }
        return null;
    }

    public static List<HoaDonNhap> timkiem_mhd(String MAHDN) {
        List<HoaDonNhap> HoaDonNhaps = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From HoaDonNhap where MAHDN LIKE ?", new String[]{"%" + MAHDN + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                HoaDonNhap HoaDonNhap = new HoaDonNhap();
                HoaDonNhap.setMahdn(cursor.getString(0));
                HoaDonNhap.setMasp(cursor.getString(1));
                HoaDonNhap.setTensp(cursor.getString(2));
                HoaDonNhap.setTennv(cursor.getString(3));
                HoaDonNhap.setSoluong(cursor.getInt(4));
                HoaDonNhap.setDongia(cursor.getInt(5));
                HoaDonNhap.setTongtien(cursor.getInt(6));
                HoaDonNhaps.add(HoaDonNhap);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return HoaDonNhaps;
    }

    public static boolean Sua_HoaDonNhap(HoaDonNhap HoaDonNhap) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MASP", HoaDonNhap.getMasp());
        contentValues.put("TENSP", HoaDonNhap.getTensp());
        contentValues.put("TENNV", HoaDonNhap.getTennv());
        contentValues.put("SOLUONG", HoaDonNhap.getSoluong());
        contentValues.put("GIATIEN", HoaDonNhap.getDongia());
        contentValues.put("TONGTIEN", HoaDonNhap.getTongtien());
        long result = MyDB.update("HoaDonNhap", contentValues, "MAHDN=?", new String[]{HoaDonNhap.getMahdn()});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_hdn(String MAHDN) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        long result = MyDB.delete("HoaDonNhap", "MAHDN=?", new String[]{MAHDN});
        if (result == -1)
            return false;
        else
            return true;
    }
}
