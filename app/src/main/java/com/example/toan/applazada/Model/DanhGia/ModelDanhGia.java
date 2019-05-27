package com.example.toan.applazada.Model.DanhGia;

import android.util.Log;

import com.example.toan.applazada.ConnectInternet.DownloadJSON;
import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDanhGia {
    public boolean themDanhGia(DanhGia danhGia) {
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        boolean kiemtra = false;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "ThemDanhGia");

        HashMap<String, String> hsMaDG = new HashMap<>();
        hsMaDG.put("madg", danhGia.getMaDG());

        HashMap<String, String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp", String.valueOf(danhGia.getMaSanPham()));

        HashMap<String, String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi", danhGia.getTenThietBi());

        HashMap<String, String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude", danhGia.getTieuDe());

        HashMap<String, String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung", danhGia.getNoiDung());

        HashMap<String, String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSoSao()));

        attrs.add(hsHam);
        attrs.add(hsMaDG);
        attrs.add(hsMaSP);
        attrs.add(hsTenThietBi);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("Kiem tra", ketqua);
            if(ketqua.equals("true")){
                kiemtra = true;
            }
            else{
                kiemtra = false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kiemtra;
    }
    public List<DanhGia> layDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGias = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>(); // dùng cho phương thức POST
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachDanhGiaTheoMaSP");

        HashMap<String, String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp", String.valueOf(masp));

        HashMap<String, String> hsLimit = new HashMap<>();
        hsLimit.put("limit", String.valueOf(limit));


        attrs.add(hsHam);
        attrs.add(hsMaSP);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);

        // Hết PT POST

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray("DANHSACHDANHGIA");
            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i < dem; i++) {
                DanhGia danhGia = new DanhGia();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                danhGia.setTenThietBi(object.getString("TENTHIETBI"));
                danhGia.setNoiDung(object.getString("NOIDUNG"));
                danhGia.setTieuDe(object.getString("TIEUDE"));
                danhGia.setSoSao(object.getInt("SOSAO"));
                danhGia.setMaSanPham(object.getInt("MASP"));
                danhGia.setMaDG(object.getString("MADG"));
                danhGia.setNgayDanhGia(object.getString("NGAYDANHGIA"));

                danhGias.add(danhGia);

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return danhGias;
    }
}
