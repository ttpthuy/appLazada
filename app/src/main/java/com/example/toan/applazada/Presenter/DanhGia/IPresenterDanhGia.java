package com.example.toan.applazada.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.example.toan.applazada.Model.ObjectClass.DanhGia;

public interface IPresenterDanhGia {
    void themDanhGia(DanhGia danhGia);
    void layDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
