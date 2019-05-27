package com.example.toan.applazada.Presenter.HienThiSanPhamTheoDanhMuc;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.toan.applazada.Model.HienThiSanPhamTheoDanhMuc.ModelHienThiSanPhamTheoDanhMuc;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicHienThiSanPhamTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc {

    ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc;
    ModelHienThiSanPhamTheoDanhMuc modelHienThiSanPhamTheoDanhMuc;

    public PresenterLogicHienThiSanPhamTheoDanhMuc(ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc) {
        this.viewHienThiSanPhamTheoDanhMuc = viewHienThiSanPhamTheoDanhMuc;
        modelHienThiSanPhamTheoDanhMuc = new ModelHienThiSanPhamTheoDanhMuc();
    }

    @Override
    public void layDanhSachSanPhamTheoMaLoai(int masp, boolean kiemtra) {
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra) {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaThuongHieu", 0);
        } else {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaLoaiDanhMuc", 0);
        }
        if (sanPhamList.size() > 0) {
            viewHienThiSanPhamTheoDanhMuc.hienThiDanhSachSanPham(sanPhamList);
        } else {
            viewHienThiSanPhamTheoDanhMuc.loiHienThiDanhSachSanPham();
        }
    }

    public List<SanPham> layDanhSachSanPhamTheoMaLoaiLoadMore(int masp, boolean kiemtra, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra) {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaThuongHieu", limit);
        } else {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp, "DANHSACHSANPHAM", "LayDanhSachSanPhamTheoMaLoaiDanhMuc", limit);
        }

        if (sanPhamList.size() != 0) {
            progressBar.setVisibility(View.GONE);
        }
        return sanPhamList;
    }
}
