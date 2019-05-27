package com.example.toan.applazada.View.DangNhap_DangKy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.toan.applazada.Adapter.ViewPagerAdapterDangNhap;
import com.example.toan.applazada.R;

public class DangNhapActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dangnhap);

        tabLayout = findViewById(R.id.tabDangNhap);
        viewPager = findViewById(R.id.viewPagerDangNhap);
        toolbar = findViewById(R.id.toolbarDangNhap);

        setSupportActionBar(toolbar);
        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);


    }
}
