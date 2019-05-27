package com.example.toan.applazada.Presenter.GioHang;

import android.content.Context;

import com.example.toan.applazada.Model.GioHang.ModelGioHang;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements IPresenterGioHang {

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang) {
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void layDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.moKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.layDanhSachSanPhamTrongGioHang();
        if(sanPhamList.size()>0){
            viewGioHang.hienThiDanhSachSanPhamTrongGioHang(sanPhamList);
        }
    }
}
