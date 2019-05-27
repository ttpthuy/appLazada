package com.example.toan.applazada.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.toan.applazada.Adapter.AdapterDienTu;
import com.example.toan.applazada.Adapter.AdapterThuongHieuLonDienTu;
import com.example.toan.applazada.Adapter.AdapterTopDienThoaiDienTu;
import com.example.toan.applazada.Model.ObjectClass.DienTu;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.Model.ObjectClass.ThuongHieu;
import com.example.toan.applazada.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.toan.applazada.R;
import com.example.toan.applazada.View.TrangChu.ViewDienTu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentDienTu extends Fragment implements ViewDienTu {
    RecyclerView recyclerView, recyclerTopCacThuogHieuLon, recyclerHangMoiVe;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView imSanPham1, imSanPham2, imSanPham3;
    TextView txtSanPham1, txtSanPham2, txtSanPham3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_dientu, container, false);
        recyclerView = view.findViewById(R.id.recyclerDienTu);
        recyclerHangMoiVe = view.findViewById(R.id.recyclerHangMoiVe);
        recyclerTopCacThuogHieuLon = view.findViewById(R.id.recyclerTopCacThuogHieuLon);
        imSanPham1 = view.findViewById(R.id.imSanPham1);
        imSanPham2 = view.findViewById(R.id.imSanPham2);
        imSanPham3 = view.findViewById(R.id.imSanPham3);
        txtSanPham1 = view.findViewById(R.id.txtSanPham1);
        txtSanPham2 = view.findViewById(R.id.txtSanPham2);
        txtSanPham3 = view.findViewById(R.id.txtSanPham3);

        dienTuList = new ArrayList<>();
        presenterLogicDienTu = new PresenterLogicDienTu(this);


        presenterLogicDienTu.layDanhSachDienTu();
        presenterLogicDienTu.layLogoThuongHieu();
        presenterLogicDienTu.layDanhSachSanPhamMoi();


        return view;
    }

    @Override
    public void hienThiDanhSach(List<DienTu> dienTus) {
        dienTuList = dienTus;

        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(), dienTuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);
        adapterDienTu.notifyDataSetChanged();
    }

    @Override
    public void hienThiLogoThuongHieu(List<ThuongHieu> thuongHieus) {
        AdapterThuongHieuLonDienTu adapterThuongHieuLonDienTu = new AdapterThuongHieuLonDienTu(getContext(), thuongHieus);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerTopCacThuogHieuLon.setLayoutManager(layoutManager);
        recyclerTopCacThuogHieuLon.setAdapter(adapterThuongHieuLonDienTu);
        adapterThuongHieuLonDienTu.notifyDataSetChanged();

    }

    @Override
    public void loiLayDuLieu() {

    }

    @Override
    public void hienThiSanPhamMoiVe(List<SanPham> sanPhams) {
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(),R.layout.custom_layout_topdienthoaimaytinhban, sanPhams);
        RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerHangMoiVe.setLayoutManager(layoutManagerTop);
        recyclerHangMoiVe.setAdapter(adapterTopDienThoaiDienTu);
        adapterTopDienThoaiDienTu.notifyDataSetChanged();

        Random random = new Random();

        int vitri1  = random.nextInt(sanPhams.size());
        int vitri2 = random.nextInt(sanPhams.size());
        int vitri3  = random.nextInt(sanPhams.size());

        Picasso.get().load(sanPhams.get(vitri1).getAnhLon()).fit().centerInside().into(imSanPham1);
        txtSanPham1.setText(sanPhams.get(vitri1).getTenSanPham());
        Picasso.get().load(sanPhams.get(vitri2).getAnhLon()).fit().centerInside().into(imSanPham2);
        txtSanPham2.setText(sanPhams.get(vitri2).getTenSanPham());
        Picasso.get().load(sanPhams.get(vitri3).getAnhLon()).fit().centerInside().into(imSanPham3);
        txtSanPham3.setText(sanPhams.get(vitri3).getTenSanPham());
    }
}
