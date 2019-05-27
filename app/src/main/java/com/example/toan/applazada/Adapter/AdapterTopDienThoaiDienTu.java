package com.example.toan.applazada.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.R;
import com.example.toan.applazada.View.ChiTietSanPham.ChiTietSanPhamActivity;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopDienThoaiDienTu extends RecyclerView.Adapter<AdapterTopDienThoaiDienTu.ViewHolderTopDienThoai> {

    Context context;
    List<SanPham> sanPhamList;
    int layout;

    public AdapterTopDienThoaiDienTu(Context context, int layout, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.layout = layout;
    }

    public class ViewHolderTopDienThoai extends RecyclerView.ViewHolder {
        ImageView imHinhSanPham;
        TextView txtTenSanPham, txtGiaTien, txtGiamGia;
        CardView cardView;

        public ViewHolderTopDienThoai(View itemView) {
            super(itemView);
            imHinhSanPham = itemView.findViewById(R.id.imTopDienThoaiDienTu);
            txtTenSanPham = itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaDienTu);
            cardView = itemView.findViewById(R.id.cartView);
        }
    }

    @Override
    public ViewHolderTopDienThoai onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout, parent, false);

        ViewHolderTopDienThoai viewHolderTopDienThoai = new ViewHolderTopDienThoai(view);

        return viewHolderTopDienThoai;
    }

    @Override
    public void onBindViewHolder(ViewHolderTopDienThoai holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        Picasso.get().load(sanPham.getAnhLon()).resize(140, 140).centerInside().into(holder.imHinhSanPham);
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGia());
        holder.txtGiaTien.setText(gia + " VNĐ ");
        holder.cardView.setTag(sanPham.getMaSP());
        holder.txtTenSanPham.setText(sanPham.getTenSanPham());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iChiTietSanPham = new Intent(context, ChiTietSanPhamActivity.class);
                iChiTietSanPham.putExtra("masp", (Integer) v.getTag());
                context.startActivity(iChiTietSanPham);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }
}
