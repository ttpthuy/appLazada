package com.example.toan.applazada.View.DanhGia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.toan.applazada.Adapter.AdapterDanhGia;
import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.Model.ObjectClass.ILoadMore;
import com.example.toan.applazada.Model.ObjectClass.LoadMoreScroll;
import com.example.toan.applazada.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.toan.applazada.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia, ILoadMore {
    RecyclerView recyclerDanhSachDanhGia;
    ProgressBar progressBar;
    int masp = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> tatCaDanhGia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_danhsachdanhgia);
        recyclerDanhSachDanhGia = findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar = findViewById(R.id.progress_bar);
        masp = getIntent().getIntExtra("masp", 0);
        tatCaDanhGia = new ArrayList<>();

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.layDanhSachDanhGiaCuaSanPham(masp, 0, progressBar);

    }

    @Override
    public void danhGiaThanhCong() {

    }

    @Override
    public void danhGiaThatBai() {

    }

    @Override
    public void hienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGias) {
        tatCaDanhGia.addAll(danhGias);
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(tatCaDanhGia, 0, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerDanhSachDanhGia.setLayoutManager(layoutManager);
        recyclerDanhSachDanhGia.setAdapter(adapterDanhGia);
        recyclerDanhSachDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterDanhGia.notifyDataSetChanged();

    }

    @Override
    public void loadMore(int tongitem) {
        presenterLogicDanhGia.layDanhSachDanhGiaCuaSanPham(masp, tongitem, progressBar);
    }
}
