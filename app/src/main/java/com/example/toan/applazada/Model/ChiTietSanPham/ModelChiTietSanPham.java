package com.example.toan.applazada.Model.ChiTietSanPham;

import android.util.Log;

import com.example.toan.applazada.ConnectInternet.DownloadJSON;
import com.example.toan.applazada.Model.ObjectClass.ChiTietSanPham;
import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {

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


    public SanPham layChiTietSanPham(String tenham, String tenmang, int masp) {
        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>(); // dùng cho phương thức POST
        String dataJSON = "";

        /*
         * Lấy dữ liệu bằng phương thức POST
         */

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);
        HashMap<String, String> hsMasp = new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));


        attrs.add(hsHam);
        attrs.add(hsMasp);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);

        // Hết PT POST

        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();
            Log.d("Kiemtra", dataJSON);
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i < dem; i++) {
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                sanPham.setMaSP(object.getInt("MASP"));
                sanPham.setTenSanPham(object.getString("TENSP"));
                sanPham.setGia(object.getInt("GIATIEN"));
                sanPham.setAnhNho(object.getString("ANHNHO"));
                sanPham.setSoLuong(object.getInt("SOLUONG"));
                sanPham.setThongTin(object.getString("THONGTIN"));
                sanPham.setMaLoaiSP(object.getInt("MALOAISP"));
                sanPham.setMaThuongHieu(object.getInt("MATHUONGHIEU"));
                sanPham.setMaNV(object.getInt("MANV"));
                sanPham.setTenNhanVien(object.getString("TENNV"));
                sanPham.setLuotMua(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSoKyThuat = object.getJSONArray("THONGSOKYTHUAT");
                for (int j = 0; j < jsonArrayThongSoKyThuat.length(); j++) {
                    JSONObject jsonObject1 = jsonArrayThongSoKyThuat.getJSONObject(j);
                    for (int k = 0; k < jsonObject1.names().length(); k++) {
                        String tenchitiet = jsonObject1.names().getString(k);
                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTenChiTiet(tenchitiet);
                        chiTietSanPham.setGiaTri(jsonObject1.getString(tenchitiet));

                        chiTietSanPhams.add(chiTietSanPham);
                    }
                }
                sanPham.setChiTietSanPhamList(chiTietSanPhams);

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sanPham;
    }
}
