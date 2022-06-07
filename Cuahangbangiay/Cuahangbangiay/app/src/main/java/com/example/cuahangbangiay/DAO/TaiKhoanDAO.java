package com.example.cuahangbangiay.DAO;

import static com.example.cuahangbangiay.GUI.MainActivityLogIn.SQLite_;
import static com.example.cuahangbangiay.GUI.MainActivityLogIn.email_;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cuahangbangiay.DTO.SanPham;
import com.example.cuahangbangiay.DTO.Taikhoan;

import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {
        //    db.execSQL("CREATE TABLE IF NOT EXISTS TaiKhoan(EMAIL TEXT PRIMARY KEY, HOTEN TEXT, MATKHAU TEXT,SDT TEXT,DIACHI TEXT)");

    public static Boolean them_taikhoan(Taikhoan tk) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", tk.getEmail());
        contentValues.put("HOTEN", tk.getHoten());
        contentValues.put("MATKHAU", tk.getMk());
        contentValues.put("SDT", tk.getSdt());
        contentValues.put("DIACHI", tk.getDiachi());
        long result = MyDB.insert("TaiKhoan", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public static Boolean kiem_tra_tk(String email) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TaiKhoan where EMAIL = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public static Boolean kiem_tra_dn(String email, String mk) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TaiKhoan where EMAIL = ? and MATKHAU = ?", new String[]{email, mk});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public static Taikhoan get_thongtin(String email) {
        Taikhoan taikhoan = new Taikhoan();
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from TaiKhoan where EMAIL = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            taikhoan.setEmail(cursor.getString(0));
            taikhoan.setHoten(cursor.getString(1));
            taikhoan.setMk(cursor.getString(2));
            taikhoan.setSdt(cursor.getString(3));
            taikhoan.setDiachi(cursor.getString(4));
            cursor.close();
            return taikhoan;
        }
        return null;
    }

    public static List<Taikhoan> getall_tk() {
        List<Taikhoan> taikhoans = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From TaiKhoan", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Taikhoan taikhoan = new Taikhoan();
                taikhoan.setEmail(cursor.getString(0));
                taikhoan.setHoten(cursor.getString(1));
                taikhoan.setMk(cursor.getString(2));
                taikhoan.setSdt(cursor.getString(3));
                taikhoan.setDiachi(cursor.getString(4));
                taikhoans.add(taikhoan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return taikhoans;
    }

    public static List<Taikhoan> timkhiekh(String hoten) {
        List<Taikhoan> taikhoans = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = SQLite_.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * From TaiKhoan where HOTEN LIKE ?", new String[]{"%" + hoten + "%"});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Taikhoan taikhoan = new Taikhoan();
                taikhoan.setEmail(cursor.getString(0));
                taikhoan.setHoten(cursor.getString(1));
                taikhoan.setMk(cursor.getString(2));
                taikhoan.setSdt(cursor.getString(3));
                taikhoan.setDiachi(cursor.getString(4));
                taikhoans.add(taikhoan);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return taikhoans;
    }

    public static boolean Sua_taikhoan(String hoten, String sdt, String diachi) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HOTEN", hoten);
        contentValues.put("SDT", sdt);
        contentValues.put("DIACHI", diachi);
        long result = MyDB.update("TaiKhoan", contentValues, "EMAIL=?", new String[]{email_});
        if (result == -1)
            return false;
        else
            return true;
    }

    public static boolean xoa_tk(String email) {
        SQLiteDatabase MyDB = SQLite_.getWritableDatabase();
        long result = MyDB.delete("TaiKhoan", "EMAIL=?", new String[]{email});
        if (result == -1)
            return false;
        else
            return true;
    }
}
