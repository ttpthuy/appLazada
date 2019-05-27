package com.example.toan.applazada.View.ChiTietSanPham;

import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void hienThiChiTietSanPham(SanPham sanPham);

    void hienThiSliderSanPham(String[] linkhinhsanpham);

    void hienThiDanhGia(List<DanhGia> danhGias);

    void themGioHangThanhCong();
    void themGioHangThatBai();
}
