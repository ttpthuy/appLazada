package com.example.toan.applazada.View.GioHang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.toan.applazada.Adapter.AdapterGioHang;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.Presenter.GioHang.PresenterLogicGioHang;
import com.example.toan.applazada.R;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang {

    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);
        recyclerView = findViewById(R.id.recyclerGioHang);
        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.layDanhSachSanPhamTrongGioHang(this);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }

    @Override
    public void hienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this, sanPhamList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);

        //adapterGioHang.notifyDataSetChanged();
    }
}
