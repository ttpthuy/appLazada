package com.example.toan.applazada.View.DanhGia;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.toan.applazada.R;

import java.util.List;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {
    TextInputLayout input_edTieuDe, input_edDanhGia;
    EditText edTieuDe, edNoiDung;
    RatingBar rbDanhGia;
    int masp;
    int sosao = 0;
    Button btnDongYDanhGia;
    PresenterLogicDanhGia presenterLogicDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themdanhgia);
        input_edTieuDe = findViewById(R.id.input_edTieuDeDanhGia);
        input_edDanhGia = findViewById(R.id.input_edNoiDungDanhGia);
        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDung = findViewById(R.id.edNoiDung);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        masp = getIntent().getIntExtra("masp", 0);
        btnDongYDanhGia = findViewById(R.id.btnDongYDanhGia);
        rbDanhGia.setOnRatingBarChangeListener(this);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);

        btnDongYDanhGia.setOnClickListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void danhGiaThanhCong() {
        Toast.makeText(this, "Đánh giá thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void danhGiaThatBai() {
        Toast.makeText(this, "Bạn không thể gửi đánh giá sản phẩm!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGias) {

    }

    @Override
    public void onClick(View v) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        String madg = telephonyManager.getDeviceId();
        String tenthietbi = Build.MODEL;
        String tieude = edTieuDe.getText().toString();
        String noidung = edNoiDung.getText().toString();


        if (tieude.trim().length() > 0) {
            input_edTieuDe.setErrorEnabled(false);
            input_edTieuDe.setError("");
        } else {
            input_edTieuDe.setErrorEnabled(true);
            input_edTieuDe.setError("Bạn chưa nhập tiêu đề đánh giá!");
        }

        if (noidung.trim().length() > 0) {
            input_edTieuDe.setErrorEnabled(false);
            input_edTieuDe.setError("");
        } else {
            input_edTieuDe.setErrorEnabled(true);
            input_edTieuDe.setError("Bạn chưa nhập nội dung đánh giá!");
        }

        if(!input_edTieuDe.isErrorEnabled() && !input_edDanhGia.isErrorEnabled()){
            DanhGia danhGia = new DanhGia();
            danhGia.setMaDG(madg);
            danhGia.setTieuDe(tieude);
            danhGia.setNoiDung(noidung);
            danhGia.setTenThietBi(tenthietbi);
            danhGia.setMaSanPham(masp);
            danhGia.setSoSao(sosao);
            presenterLogicDanhGia.themDanhGia(danhGia);
            finish();
        }
    }
}
