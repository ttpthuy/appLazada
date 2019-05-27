package com.example.toan.applazada.Model.TrangChu.XuLyMenu;

import android.os.Bundle;
import android.util.Log;

import com.example.toan.applazada.ConnectInternet.DownloadJSON;
import com.example.toan.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XuLyJSONMenu {
    String tenNguoiDung;

    public List<LoaiSanPham> parseJSONMenu(String dulieujson) {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

        try {
            Log.d("kiemtra", dulieujson);
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();

            for (int i = 0; i < count; i++) {
                JSONObject value = loaisanpham.getJSONObject(i);
                LoaiSanPham dataloaiSanPham = new LoaiSanPham();

                dataloaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataloaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataloaiSanPham.setTENLOAISP(value.getString("TENLOAISP"));

                loaiSanPhamList.add(dataloaiSanPham);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;
    }

    public List<LoaiSanPham> layLoaiSanPhamTheoMaLoai(int maloaisp) {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<LoaiSanPham>();
        List<HashMap<String, String>> attrs = new ArrayList<>(); // dùng cho phương thức POST
        String dataJSON = "";

        /*
         * Lấy dữ liệu bằng phương thức POST
         */

        String duongdan = TrangChuActivity.SERVER_NAME;
        // String duongdan = "http://10.0.3.2/weblazada2/loaisanpham.php";

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "layDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", String.valueOf(maloaisp));

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);

        // Hết PT POST

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.parseJSONMenu(dataJSON);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return loaiSanPhamList;
    }


}
