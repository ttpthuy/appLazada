package com.example.toan.applazada.View.TrangChu;

import com.example.toan.applazada.Model.ObjectClass.DienTu;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu{
    void hienThiDanhSach(List<DienTu> dienTus);
    void hienThiLogoThuongHieu(List<ThuongHieu> thuongHieus);
    void loiLayDuLieu();
    void hienThiSanPhamMoiVe(List<SanPham> sanPhams);
}
