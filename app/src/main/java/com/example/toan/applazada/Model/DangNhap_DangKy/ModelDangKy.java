package com.example.toan.applazada.Model.DangNhap_DangKy;

import android.util.Log;

import com.example.toan.applazada.ConnectInternet.DownloadJSON;
import com.example.toan.applazada.Model.ObjectClass.NhanVien;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangKy {

    public boolean dangKyThanhVien(NhanVien nhanVien) {
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "dangKyThanhVien");

        HashMap<String, String> hsTenNV = new HashMap<>();
        hsTenNV.put("tennv", nhanVien.getTenNV());

        HashMap<String, String> hsTenDN = new HashMap<>();
        hsTenDN.put("tendangnhap", nhanVien.getTenDN());

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", nhanVien.getMatKhau());

        HashMap<String, String> hsMaLoaiNV = new HashMap<>();
        hsMaLoaiNV.put("maloainv", String.valueOf(nhanVien.getMaLoaiNV()));

        HashMap<String, String> hsEmaildocquyen= new HashMap<>();
        hsEmaildocquyen.put("emaildocquyen", nhanVien.getEmailDocQuyen());

        attrs.add(hsHam);
        attrs.add(hsTenNV);
        attrs.add(hsTenDN);
        attrs.add(hsMatKhau);
        attrs.add(hsMaLoaiNV);
        attrs.add(hsEmaildocquyen);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            Log.d("Kiem tra", dulieu);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
