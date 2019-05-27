package com.example.toan.applazada.Model.HienThiSanPhamTheoDanhMuc;

import com.example.toan.applazada.ConnectInternet.DownloadJSON;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelHienThiSanPhamTheoDanhMuc {

    public List<SanPham> layDanhSachSanPhamTheoMaLoai(int masp, String tenmang, String tenham, int limit) {
        List<SanPham> sanPhams = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>(); // dùng cho phương thức POST
        String dataJSON = "";

        /*
         * Lấy dữ liệu bằng phương thức POST
         */

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        HashMap<String, String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("maloaisp", String.valueOf(masp));

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));


        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);

        // Hết PT POST

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i < dem; i++) {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                sanPham.setMaSP(object.getInt("MASP"));
                sanPham.setTenSanPham(object.getString("TENSP"));
                sanPham.setGia(object.getInt("GIATIEN"));
                sanPham.setAnhLon(object.getString("HINHSANPHAM"));
                sanPham.setAnhNho(object.getString("HINHSANPHAMNHO"));

                sanPhams.add(sanPham);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPhams;
    }
}
