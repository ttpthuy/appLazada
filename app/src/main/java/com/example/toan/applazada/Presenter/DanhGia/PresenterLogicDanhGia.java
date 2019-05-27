package com.example.toan.applazada.Presenter.DanhGia;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.toan.applazada.Model.DanhGia.ModelDanhGia;
import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.View.DanhGia.ViewDanhGia;

import java.util.List;

public class PresenterLogicDanhGia implements IPresenterDanhGia {

    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;

    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia) {
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void themDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.themDanhGia(danhGia);
        if (kiemtra) {
            viewDanhGia.danhGiaThanhCong();
        } else {
            viewDanhGia.danhGiaThatBai();
        }
    }

    @Override
    public void layDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        List<DanhGia> danhGias = modelDanhGia.layDanhSachDanhGiaCuaSanPham(masp, limit);
        if(danhGias.size()>0){
            viewDanhGia.hienThiDanhSachDanhGiaTheoSanPham(danhGias);
            progressBar.setVisibility(View.GONE);
        }
    }

}
