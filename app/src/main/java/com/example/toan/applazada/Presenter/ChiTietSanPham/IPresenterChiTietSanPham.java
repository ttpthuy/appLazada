package com.example.toan.applazada.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.toan.applazada.Model.ObjectClass.SanPham;

public interface IPresenterChiTietSanPham {
    void layChiTietSanPham(int masp);

    void layDanhSachDanhGiaCuaSanPham(int masp, int limit);

    void themGioHang(SanPham sanPham, Context context);


}
