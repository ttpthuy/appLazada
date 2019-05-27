package com.example.toan.applazada.View.DangNhap_DangKy.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.toan.applazada.Model.ObjectClass.NhanVien;
import com.example.toan.applazada.Presenter.TrangDangKy.PresenterLogicDangKy;
import com.example.toan.applazada.R;
import com.example.toan.applazada.View.DangNhap_DangKy.ViewDangKy;

import java.util.regex.Pattern;

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener {
    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy;
    EditText edHoTen, edMatKhau, edNhapLaiMatKhau, edMail;
    SwitchCompat sEmailDocQuyen;
    TextInputLayout input_edHoTen, input_edMatKhau, input_edNhapLaiMatKhau, input_edEmail;
    boolean kiemtrathongtindk = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky, container, false);
        presenterLogicDangKy = new PresenterLogicDangKy(this);

        //Khởi tạo đối tượng
        btnDangKy = view.findViewById(R.id.btnDangKy);
        edHoTen = view.findViewById(R.id.edHoTenDK);
        edMail = view.findViewById(R.id.edDiaChiEmailDK);
        edMatKhau = view.findViewById(R.id.edMatKhauDK);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhauDK);
        sEmailDocQuyen = view.findViewById(R.id.sEmailDocQuyen);
        input_edHoTen = view.findViewById(R.id.input_edHoTenDK);
        input_edEmail = view.findViewById(R.id.input_edDiaChiEmailDK);
        input_edMatKhau = view.findViewById(R.id.input_edMatKhauDK);
        input_edNhapLaiMatKhau = view.findViewById(R.id.input_edNhapLaiMatKhauDK);

        btnDangKy.setOnClickListener(this);
        edHoTen.setOnFocusChangeListener(this);
        edMail.setOnFocusChangeListener(this);
        edNhapLaiMatKhau.setOnFocusChangeListener(this);


        return view;
    }

    @Override
    public void dangKyThanhCong() {

    }

    @Override
    public void dangKyThatBai() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDangKy:
                btnDangky();
                break;
            case R.id.btnDangNhapFacebookDK:
                break;
            case R.id.btnDangNhapGoogleDK:
                break;
        }
    }

    String emaildocquyen = "";

    private void btnDangky() {
        String hoten = edHoTen.getText().toString();
        String email = edMail.getText().toString();
        String matkhau = edMatKhau.getText().toString();
        String nhaplaimatkhau = edNhapLaiMatKhau.getText().toString();
        sEmailDocQuyen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                emaildocquyen = isChecked + "";
            }
        });
        if (kiemtrathongtindk) {
            NhanVien nv = new NhanVien();
            nv.setTenNV(hoten);
            nv.setTenDN(email);
            nv.setMatKhau(matkhau);
            nv.setEmailDocQuyen(emaildocquyen);
            nv.setMaLoaiNV(2);

            presenterLogicDangKy.thucHienDangKy(nv);
        }else {
            Log.d("Dang ky", "Đăng ký thất bại!");
        }


    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        int id = view.getId();
        switch (id) {
            case R.id.edHoTenDK:
                if (!hasFocus) {
                    TextInputLayout textInputLayout = (TextInputLayout) view.getParent().getParent();
                    String chuoi = ((EditText) view).getText().toString();
                    if (chuoi.trim().equals("") || chuoi.equals(null)) {
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Không được để trống!");
                        kiemtrathongtindk = false;
                    } else {
                        textInputLayout.setErrorEnabled(false);
                        textInputLayout.setError("");
                        kiemtrathongtindk = true;
                    }
                }
                break;
            case R.id.edDiaChiEmailDK:
                if (!hasFocus) {
                    TextInputLayout textInputLayout = (TextInputLayout) view.getParent().getParent();
                    String chuoi = ((EditText) view).getText().toString();


                    if (chuoi.trim().equals("") || chuoi.equals(null)) { // Ko nhập email
                        textInputLayout.setErrorEnabled(true);
                        textInputLayout.setError("Không được để trống!");
                        kiemtrathongtindk = false;
                    } else {
                        boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if (!kiemtraemail) {// Nhập email ko hợp lệ
                            textInputLayout.setErrorEnabled(true);
                            textInputLayout.setError("Định dạng email không hợp lệ!\nExample: example@domain.abc");
                            kiemtrathongtindk = false;
                        } else {// Nhập email hợp lệ
                            textInputLayout.setErrorEnabled(false);
                            textInputLayout.setError("");
                            kiemtrathongtindk = true;
                        }
                    }
                }
                break;
            case R.id.edMatKhauDK:

                break;
            case R.id.edNhapLaiMatKhauDK:
                if (!hasFocus) {
                    String chuoi = ((EditText) view).getText().toString();
                    String matkhau = edMatKhau.getText().toString();
                    if (!chuoi.equals(matkhau)) {
                        input_edNhapLaiMatKhau.setErrorEnabled(true);
                        input_edNhapLaiMatKhau.setError("Mật khẩu không khớp nhau!");
                        kiemtrathongtindk = false;
                    } else {
                        input_edNhapLaiMatKhau.setErrorEnabled(false);
                        input_edNhapLaiMatKhau.setError("");
                        kiemtrathongtindk = true;
                    }
                }
                break;
        }
    }
}
