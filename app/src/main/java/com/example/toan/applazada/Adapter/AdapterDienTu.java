package com.example.toan.applazada.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.toan.applazada.Model.ObjectClass.DienTu;
import com.example.toan.applazada.R;

import java.util.List;

public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolderDienTu> {

    Context context;
    List<DienTu> dienTus;

    public AdapterDienTu(Context context, List<DienTu> dienTus) {
        this.context = context;
        this.dienTus = dienTus;
    }

    public class ViewHolderDienTu extends RecyclerView.ViewHolder {
        ImageView imHinhGiamGia;
        RecyclerView recyclerViewThuongHieuLon, recyclerViewTopSanPham;
        TextView txtTieuDeSanPhamNoiBat, txtTopSanPhamNoiBat;

        public ViewHolderDienTu(View itemView) {
            super(itemView);
            imHinhGiamGia = itemView.findViewById(R.id.imKhuyenMaiDienTu);
            recyclerViewThuongHieuLon = itemView.findViewById(R.id.recycleThuongHieuLon);
            recyclerViewTopSanPham = itemView.findViewById(R.id.recycleTopDienThoaiMayTinhBang);
            txtTieuDeSanPhamNoiBat = itemView.findViewById(R.id.txtTenSanPhamNoiBat);
            txtTopSanPhamNoiBat = itemView.findViewById(R.id.txtTenTopSanPhamNoiBat);
        }
    }

    @Override
    public ViewHolderDienTu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recyclerview_dientu, parent, false);
        ViewHolderDienTu viewHolderDienTu = new ViewHolderDienTu(view);
        return viewHolderDienTu;
    }

    @Override
    public void onBindViewHolder(ViewHolderDienTu holder, int position) {

        DienTu dienTu = dienTus.get(position);

        holder.txtTieuDeSanPhamNoiBat.setText(dienTu.getTennoibat());
        holder.txtTopSanPhamNoiBat.setText(dienTu.getTentopnoibat());

        // Xử lý hiển thị danh sách thương hiệu lớn (RecyclerViewThuongHieuLon)
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context, dienTu.getThuongHieus(), dienTu.isThuonghieu());

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);
        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieuLon.setAdapter(adapterThuongHieuLon);
        adapterThuongHieuLon.notifyDataSetChanged();

        // Xử lý hiển thị danh sách top sản phẩm (RecyclerViewTopSanPham)
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context,R.layout.custom_layout_topdienthoaimaytinhban ,dienTu.getSanPhams());
        RecyclerView.LayoutManager layoutManagerTop = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerViewTopSanPham.setLayoutManager(layoutManagerTop);
        holder.recyclerViewTopSanPham.setAdapter(adapterTopDienThoaiDienTu);

    }

    @Override
    public int getItemCount() {
        return dienTus.size();
    }
}
