package com.example.toan.applazada.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.toan.applazada.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ModelGioHang {
    SQLiteDatabase database;

    public void moKetNoiSQL(Context context) {
        DatabaseSanPham databaseSanPham = new DatabaseSanPham(context);
        database = databaseSanPham.getWritableDatabase();
    }

    public boolean themGioHang(SanPham sanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSanPham.TB_GIOHANG_MASP, sanPham.getMaSP());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_TENSP, sanPham.getTenSanPham());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_GIATIEN, sanPham.getGia());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_HINHANH, sanPham.getHinhgiohang());
        contentValues.put(DatabaseSanPham.TB_GIOHANG_SOLUONG, sanPham.getSoLuong());

        long id = database.insert(DatabaseSanPham.TB_GIOHANG, null, contentValues);

        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean xoaSanPhamTrongGioHang(int masp) {
        int kiemtra = database.delete(DatabaseSanPham.TB_GIOHANG, DatabaseSanPham.TB_GIOHANG_MASP + "=" + masp, null);
        if (kiemtra > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean capNhatSoLuongSanPhamGioHang(int masp, int soluong) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseSanPham.TB_GIOHANG_SOLUONG, soluong);
        int id = database.update(DatabaseSanPham.TB_GIOHANG, contentValues, DatabaseSanPham.TB_GIOHANG_MASP + "=" + masp, null);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<SanPham> layDanhSachSanPhamTrongGioHang() {
        List<SanPham> sanPhamList = new ArrayList<>();
        String truyvan = "SELECT * FROM " + DatabaseSanPham.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyvan, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int masp = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_MASP));
            int soluong = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_SOLUONG));
            String tensp = cursor.getString(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DatabaseSanPham.TB_GIOHANG_HINHANH));

            SanPham sanPham = new SanPham();
            sanPham.setMaSP(masp);
            sanPham.setSoLuong(soluong);
            sanPham.setTenSanPham(tensp);
            sanPham.setGia(giatien);
            sanPham.setHinhgiohang(hinhanh);

            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }

        return sanPhamList;
    }


}
