package com.example.cuahangbangiay.DAO;

import static com.example.cuahangbangiay.GUI.MainActivityLogIn.SQLite_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cuahangbangiay.DTO.HoaDonBan;

import java.util.ArrayList;
import java.util.List;

public class HoaDonXuatDAO {

    public static Boolean Them_HDX(HoaDonBan HoaDonBan) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("idgh", HoaDonBan.getIdgh());
        long result = MyDB.insert("HoaDonXuat", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static List<HoaDonBan> DS_HDX() {
        List<HoaDonBan> hoaDonBans = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From HoaDonXuat", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                HoaDonBan HoaDonBan = new HoaDonBan();
                HoaDonBan.setIddh(cursor.getInt(0));
                HoaDonBan.setIdgh(cursor.getInt(1));
                hoaDonBans.add(HoaDonBan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hoaDonBans;
    }


    public static List<HoaDonBan> timkiem_hdb(String iddh) {
        List<HoaDonBan> hoaDonBans = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From HoaDonXuat where iddh LIKE ?", new String[]{"%" + iddh + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                HoaDonBan HoaDonBan = new HoaDonBan();
                HoaDonBan.setIddh(cursor.getInt(0));
                HoaDonBan.setIdgh(cursor.getInt(1));
                hoaDonBans.add(HoaDonBan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return hoaDonBans;
    }


    public static boolean xoa_hdx(int iddh) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        long result = MyDB.delete("HoaDonXuat", "iddh=?", new String[]{String.valueOf(iddh)});
        if (result == -1)
            return false;
        else
            return true;
    }
}
