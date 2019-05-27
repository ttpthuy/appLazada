package com.example.toan.applazada.Model.TrangChu_DienTu;

import com.example.toan.applazada.ConnectInternet.DownloadJSON;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.Model.ObjectClass.ThuongHieu;
import com.example.toan.applazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDienTu {



    public List<SanPham> layDanhSachSanPhamTOP(String tenham, String tenmang) {
        List<SanPham> sanPhams = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>(); // dùng cho phương thức POST
        String dataJSON = "";

        /*
         * Lấy dữ liệu bằng phương thức POST
         */

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);


        attrs.add(hsHam);

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


    public List<ThuongHieu> layDanhSachThuongHieuLon(String tenham, String tenmang) {
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>(); // dùng cho phương thức POST
        String dataJSON = "";

        /*
         * Lấy dữ liệu bằng phương thức POST
         */

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);


        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);

        // Hết PT POST

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachThuongHieu = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachThuongHieu.length();

            for (int i = 0; i < dem; i++) {
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDanhSachThuongHieu.getJSONObject(i);
                thuongHieu.setMaThuongHieu(object.getInt("MASP"));
                thuongHieu.setTenThuongHieu(object.getString("TENSP"));
                thuongHieu.setHinhThuongHieu(object.getString("HINHSANPHAM"));

                thuongHieuList.add(thuongHieu);
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thuongHieuList;
    }

}
