package com.example.toan.applazada.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.toan.applazada.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.toan.applazada.Model.GioHang.ModelGioHang;
import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;

    public PresenterLogicChiTietSanPham() {
        modelGioHang = new ModelGioHang();
    }

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void layChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.layChiTietSanPham("LaySanPhamVsChitietTheoMaSP", "CHITIETSANPHAM", masp);
        if (sanPham.getMaSP() > 0) {
            String[] linkhinhanh = sanPham.getAnhNho().split(",");
            viewChiTietSanPham.hienThiSliderSanPham(linkhinhanh);
            viewChiTietSanPham.hienThiChiTietSanPham(sanPham);
        }
    }

    @Override
    public void layDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGiaList = modelChiTietSanPham.layDanhSachDanhGiaCuaSanPham(masp, limit);
        if (danhGiaList.size() > 0) {
            viewChiTietSanPham.hienThiDanhGia(danhGiaList);
        }

    }

    @Override
    public void themGioHang(SanPham sanPham, Context context) {
        modelGioHang.moKetNoiSQL(context);
        boolean kiemtra = modelGioHang.themGioHang(sanPham);
        if (kiemtra) {
            viewChiTietSanPham.themGioHangThanhCong();
        } else {
            viewChiTietSanPham.themGioHangThatBai();
        }
    }

    public int demSoLuongSanPhamCoTrongGioHang(Context context){
        modelGioHang.moKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.layDanhSachSanPhamTrongGioHang();
        int dem = sanPhamList.size();
        return dem;
    }
}
