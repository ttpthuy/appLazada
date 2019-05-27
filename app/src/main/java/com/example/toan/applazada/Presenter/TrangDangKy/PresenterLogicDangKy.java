package com.example.toan.applazada.Presenter.TrangDangKy;

import com.example.toan.applazada.Model.DangNhap_DangKy.ModelDangKy;
import com.example.toan.applazada.Model.ObjectClass.NhanVien;
import com.example.toan.applazada.View.DangNhap_DangKy.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy) {
        this.viewDangKy = viewDangKy;
        this.modelDangKy = new ModelDangKy();
    }

    @Override
    public void thucHienDangKy(NhanVien nhanVien) {
        modelDangKy.dangKyThanhVien(nhanVien);
    }
}
