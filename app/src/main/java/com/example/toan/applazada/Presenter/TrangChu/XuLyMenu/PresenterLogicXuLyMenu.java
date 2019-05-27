package com.example.toan.applazada.Presenter.TrangChu.XuLyMenu;

import com.example.toan.applazada.ConnectInternet.DownloadJSON;
import com.example.toan.applazada.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.toan.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.toan.applazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;
import com.example.toan.applazada.View.TrangChu.ViewXuLyMenu;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {

    ViewXuLyMenu viewXuLyMenu;
    String tenNguoiDung="";

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void layDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        List<HashMap<String, String>> attrs = new ArrayList<>(); // dùng cho phương thức POST

        /*
        * Lấy dữ liệu bằng phương thức GET

        String duongdan = "http://10.0.3.2/weblazada2/loaisanpham.php?maloaicha=0";
        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        // Hết PT GET
        */
        // ----------------------------------------------------------------------------- //
        /*
         * Lấy dữ liệu bằng phương thức POST
         */

       String duongdan = TrangChuActivity.SERVER_NAME + "/weblazada2/loaisanpham.php";
      //  String duongdan = "http://10.0.3.2/weblazada2/loaisanpham.php";

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "layDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", "0");

        attrs.add(hsMaLoaiCha);
        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);

        // Hết PT POST

        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.parseJSONMenu(dataJSON);
            viewXuLyMenu.hienThiDanhSachMenu(loaiSanPhamList);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken layTokenNguoiDungFacebook() {

        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.layTokenFacebookHienTai();

        return accessToken;
    }
}
