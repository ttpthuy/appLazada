package com.example.toan.applazada.View.ChiTietSanPham;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toan.applazada.Adapter.AdapterDanhGia;
import com.example.toan.applazada.Adapter.AdapterViewPagerSlider;
import com.example.toan.applazada.Model.ObjectClass.ChiTietSanPham;
import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.toan.applazada.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.toan.applazada.R;
import com.example.toan.applazada.View.DanhGia.DanhSachDanhGiaActivity;
import com.example.toan.applazada.View.DanhGia.ThemDanhGiaActivity;
import com.example.toan.applazada.View.GioHang.GioHangActivity;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener, View.OnClickListener {

    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots;
    List<Fragment> fragmentList;
    TextView txtTenSanPham, txtGiaTien, txtTenCHDongGoi, txtThongTinChiTiet, txtVietDanhGia, txtXemTatCaCacNhanXet, txtGioHang;
    Toolbar toolbar;
    ImageView imXemThemThongTin, imThemGioHang;
    boolean kiemtraxochitiet = false;
    LinearLayout lnThongSoKyThuat;
    int masp;
    List<DanhGia> danhGiaList;
    RecyclerView recyclerViewDanhGiaChiTiet;
    SanPham sanPhamGioHang;
    boolean onPause = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chitietsanpham);
        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutDots);
        txtTenSanPham = findViewById(R.id.txtTenSanPham);
        txtGiaTien = findViewById(R.id.txtGiaTien);
        toolbar = findViewById(R.id.toolbar);
        txtTenCHDongGoi = findViewById(R.id.txtTenCHDongGoi);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        imXemThemThongTin = findViewById(R.id.imXemThongTinChiTiet);
        lnThongSoKyThuat = findViewById(R.id.lnThongSoKyThuat);
        txtVietDanhGia = findViewById(R.id.txtVietDanhGia);
        recyclerViewDanhGiaChiTiet = findViewById(R.id.recyclerDanhGiaChiTiet);
        txtXemTatCaCacNhanXet = findViewById(R.id.txtXemTatCacNhanXet);
        imThemGioHang = findViewById(R.id.imThemGioHang);

        setSupportActionBar(toolbar);
        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.layChiTietSanPham(masp);
        presenterLogicChiTietSanPham.layDanhSachDanhGiaCuaSanPham(masp, 0);

        txtVietDanhGia.setOnClickListener(this);
        txtXemTatCaCacNhanXet.setOnClickListener(this);
        imThemGioHang.setOnClickListener(this);
    }

    @Override
    public void hienThiChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMaSP();

        sanPhamGioHang = sanPham;

        txtTenSanPham.setText(sanPham.getTenSanPham());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGia());
        txtGiaTien.setText(gia + " VNĐ");
        txtTenCHDongGoi.setText(sanPham.getTenNhanVien());

        txtThongTinChiTiet.setText(sanPham.getThongTin().substring(0, 100));
        if (sanPham.getThongTin().length() < 100) {
            imXemThemThongTin.setVisibility(View.GONE);
        } else {
            imXemThemThongTin.setVisibility(View.VISIBLE);
            imXemThemThongTin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    kiemtraxochitiet = !kiemtraxochitiet;
                    if (kiemtraxochitiet) {
                        // Sau khi xổ chi tiết
                        txtThongTinChiTiet.setText(sanPham.getThongTin());
                        //imXemThemThongTin.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        imXemThemThongTin.setImageDrawable(LayHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        hienThiThongSoKyThuat(sanPham);
                    } else {
                        // Sau khi đóng chi tiết
                        txtThongTinChiTiet.setText(sanPham.getThongTin().substring(0, 100));
                        // imXemThemThongTin.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        imXemThemThongTin.setImageDrawable(LayHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                        lnThongSoKyThuat.setVisibility(View.GONE);
                    }
                }
            });
        }


    }

    private void hienThiThongSoKyThuat(SanPham sanPham) {
        List<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();
        TextView txtTieuDeThongSo = new TextView(this);
        txtTieuDeThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtTieuDeThongSo.setText("Thông số kỹ thuật");
        txtTieuDeThongSo.setTextSize(16f);
        lnThongSoKyThuat.addView(txtTieuDeThongSo);
        for (int i = 0; i < chiTietSanPhams.size(); i++) {
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);

            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtTenThongSo.setText(chiTietSanPhams.get(i).getTenChiTiet() + ":");

            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 2.0f));
            txtGiaTriThongSo.setText(chiTietSanPhams.get(i).getGiaTri());

            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);
        }
    }

    private Drawable LayHinhChiTiet(int idDrawable) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 21) {
            drawable = ContextCompat.getDrawable(this, idDrawable);
        } else {
            drawable = getResources().getDrawable(idDrawable);
        }
        return drawable;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);

        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        //View giaoDienCustomGioHang = MenuItemCompat.getActionView();
        View giaoDienCustomGioHang = iGioHang.getActionView(); // Tự sửa theo trên mạng
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.demSoLuongSanPhamCoTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
        return true;
    }

    @Override
    public void hienThiSliderSanPham(String[] linkhinhsanpham) {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < linkhinhsanpham.length; i++) {
            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", TrangChuActivity.SERVER + linkhinhsanpham[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }
        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();

        themDotSlider(0);
        viewPager.addOnPageChangeListener(this);

    }


    public void themDotSlider(int vitrihientai) {
        txtDots = new TextView[fragmentList.size()];
        layoutDots.removeAllViews();
        for (int i = 0; i < fragmentList.size(); i++) {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(35);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));

            layoutDots.addView(txtDots[i]);
        }
        txtDots[vitrihientai].setTextColor(getIdColor(R.color.colorToolbar));
    }

    private int getIdColor(int idColor) {

        int color = 0;
        if (Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this, color);
        } else {
            color = getResources().getColor(idColor);
        }
        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        themDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txtVietDanhGia:
                Intent iThemDanhGia = new Intent(this, ThemDanhGiaActivity.class);
                iThemDanhGia.putExtra("masp", masp);
                startActivity(iThemDanhGia);
                break;
            case R.id.txtXemTatCacNhanXet:
                Intent iDanhSachDanhGia = new Intent(this, DanhSachDanhGiaActivity.class);
                iDanhSachDanhGia.putExtra("masp", masp);
                startActivity(iDanhSachDanhGia);
                break;
            case R.id.imThemGioHang:
                Fragment fragment = fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.imHinhSlider);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                sanPhamGioHang.setHinhgiohang(hinhsanphamgiohang);
                sanPhamGioHang.setSoLuong(1);

                presenterLogicChiTietSanPham.themGioHang(sanPhamGioHang, this);
                break;
        }
    }

    @Override
    public void hienThiDanhGia(List<DanhGia> danhGiaList) {
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(danhGiaList, 3, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerViewDanhGiaChiTiet.setAdapter(adapterDanhGia);
        recyclerViewDanhGiaChiTiet.setLayoutManager(layoutManager);
        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void themGioHangThanhCong() {
        Toast.makeText(this, "Thêm giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.demSoLuongSanPhamCoTrongGioHang(this)));
    }

    @Override
    public void themGioHangThatBai() {
        Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng!", Toast.LENGTH_SHORT).show();
    }

    protected void onResume() {
        super.onResume();
        if (onPause) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.demSoLuongSanPhamCoTrongGioHang(this)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
}
