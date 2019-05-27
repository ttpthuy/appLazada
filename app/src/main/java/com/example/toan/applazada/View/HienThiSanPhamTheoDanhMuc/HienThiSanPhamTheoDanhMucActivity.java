package com.example.toan.applazada.View.HienThiSanPhamTheoDanhMuc;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.toan.applazada.Adapter.AdapterTopDienThoaiDienTu;
import com.example.toan.applazada.Model.ObjectClass.ILoadMore;
import com.example.toan.applazada.Model.ObjectClass.LoadMoreScroll;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.toan.applazada.R;
import com.example.toan.applazada.View.TrangChu.ViewHienThiSanPhamTheoDanhMuc;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener, ILoadMore {
    RecyclerView recyclerView;
    Button btnThayDoiTrangThaiRecycler;
    boolean danggrid = true;
    RecyclerView.LayoutManager layoutManager;
    PresenterLogicHienThiSanPhamTheoDanhMuc sanPhamTheoDanhMuc;
    int masp;
    boolean kiemtra;
    AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu;
    android.support.v7.widget.Toolbar toolbar;
    ProgressBar progressBar;
    List<SanPham> sanPhamList1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthisanphamtheodanhmuc);

        recyclerView = findViewById(R.id.recycleViewHienThiSanPhamTheoDanhMuc);
        btnThayDoiTrangThaiRecycler = findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progress_bar);

        Intent intent = getIntent();
        masp = intent.getIntExtra("MALOAI", 0);
        String tensanpham = intent.getStringExtra("TENLOAI");
        kiemtra = intent.getBooleanExtra("KIEMTRA", false);

        //Toast.makeText(this, masp + "-" + tensanpham + "-" + kiemtra, Toast.LENGTH_SHORT).show();

        sanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        sanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp, kiemtra);

        btnThayDoiTrangThaiRecycler.setOnClickListener(this);
        toolbar.setTitle(tensanpham);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public void hienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        sanPhamList1 = sanPhamList;
        if (danggrid) {
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this, 2);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this, R.layout.custom_layout_topdienthoaimaytinhban, sanPhamList1);
        } else {
            layoutManager = new LinearLayoutManager(HienThiSanPhamTheoDanhMucActivity.this);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this, R.layout.custom_layout_list_topdienthoaivamaytinhban, sanPhamList1);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public void loiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnThayDoiTrangThaiRecycler:
                danggrid = !danggrid;
                sanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoai(masp, kiemtra);
                break;
        }
    }

    @Override
    public void loadMore(int tongitem) {
       List<SanPham> listsanPhamsLoadMore = sanPhamTheoDanhMuc.layDanhSachSanPhamTheoMaLoaiLoadMore(masp, kiemtra, tongitem, progressBar);
       sanPhamList1.addAll(listsanPhamsLoadMore);

       adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }
}
