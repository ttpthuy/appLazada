package com.example.toan.applazada.Presenter.TrangChu_DienTu;

import com.example.toan.applazada.Model.ObjectClass.DienTu;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.Model.ObjectClass.ThuongHieu;
import com.example.toan.applazada.Model.TrangChu_DienTu.ModelDienTu;
import com.example.toan.applazada.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements IPresenterDienTu {
    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;

    public PresenterLogicDienTu(ViewDienTu viewDienTu) {
        this.viewDienTu = viewDienTu;
        this.modelDienTu = new ModelDienTu();
    }

    @Override
    public void layDanhSachDienTu() {
        List<DienTu> dienTus = new ArrayList<>();
        List<ThuongHieu> thuongHieuList = modelDienTu.layDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon", "DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelDienTu.layDanhSachSanPhamTOP("LayDanhSachTopDienThoaiVaMayTinhBang", "TOPDIENTHOAI&MAYTINHBANG");


        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSanPhams(sanPhamList);
        dienTu.setTennoibat("Thương hiệu lớn");
        dienTu.setTentopnoibat("Top Điện thoại & Máy tính bảng");
        dienTu.setThuonghieu(true);
        dienTus.add(dienTu);


        List<SanPham> phukienList = modelDienTu.layDanhSachSanPhamTOP("LayDanhSachTopPhuKien", "TOPPHUKIEN");
        List<ThuongHieu> topphukienList = modelDienTu.layDanhSachThuongHieuLon("LayDanhSachPhuKien", "DANHSACHPHUKIEN");

        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(topphukienList);
        dienTu1.setSanPhams(phukienList);
        dienTu1.setTennoibat("Phụ kiện");
        dienTu1.setTentopnoibat("Top Phụ kiện");
        dienTu1.setThuonghieu(false);
        dienTus.add(dienTu1);

        List<SanPham> tienichList = modelDienTu.layDanhSachSanPhamTOP("LayTopTienIch", "TOPTIENICH");
        List<ThuongHieu> toptienichList = modelDienTu.layDanhSachThuongHieuLon("LayDanhSachTienIch", "DANHSACHTIENICH");

        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(toptienichList);
        dienTu2.setSanPhams(tienichList);
        dienTu2.setTennoibat("Tiện ích");
        dienTu2.setTentopnoibat("Top Videos & TV");
        dienTu2.setThuonghieu(false);
        dienTus.add(dienTu2);

        if (thuongHieuList.size() > 0 && sanPhamList.size() > 0) {
            viewDienTu.hienThiDanhSach(dienTus);
        }
    }

    @Override
    public void layLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = modelDienTu.layDanhSachThuongHieuLon("LayLogoThuongHieuLon", "DANHSACHTHUONGHIEU");
        if (thuongHieuList.size() > 0) {
            viewDienTu.hienThiLogoThuongHieu(thuongHieuList);
        } else {
            viewDienTu.loiLayDuLieu();
        }
    }

    @Override
    public void layDanhSachSanPhamMoi() {
        List<SanPham> sanPhamList = modelDienTu.layDanhSachSanPhamTOP("LayDanhSachSanPhamMoi", "DANHSACHSANPHAMMOIVE");
        if (sanPhamList.size() > 0) {
            viewDienTu.hienThiSanPhamMoiVe(sanPhamList);

        } else {
            viewDienTu.loiLayDuLieu();
        }
    }
}
