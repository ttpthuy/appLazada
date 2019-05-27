package com.example.toan.applazada.View.DanhGia;

import com.example.toan.applazada.Model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhGia {
    void danhGiaThanhCong();
    void danhGiaThatBai();
    void hienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGias);
}
