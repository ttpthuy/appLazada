package com.example.toan.applazada.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.toan.applazada.Model.GioHang.ModelGioHang;
import com.example.toan.applazada.Model.ObjectClass.SanPham;
import com.example.toan.applazada.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {

    Context context;
    List<SanPham> sanPhamList;
    ModelGioHang modelGioHang;


    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.moKetNoiSQL(context);
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTieuDeGioHang, txtGiaTienGioHang;
        ImageView imHinhSanPhamGioHang, imXoaSanPhamGioHang;
        ImageView imGiamSoLuongSPTrongGioHang, imTangSoLuongSPTrongGioHang;
        TextView txtSoLuongSPTrongGioHang;

        public ViewHolderGioHang(View itemView) {
            super(itemView);
            txtTieuDeGioHang = itemView.findViewById(R.id.txtTieuDeGioHang);
            txtGiaTienGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            imHinhSanPhamGioHang = itemView.findViewById(R.id.imHinhGioHang);
            imXoaSanPhamGioHang = itemView.findViewById(R.id.imXoaSanPhamGioHang);
            imGiamSoLuongSPTrongGioHang = itemView.findViewById(R.id.imGiamSoLuongSPTrongGioHang);
            imTangSoLuongSPTrongGioHang = itemView.findViewById(R.id.imTangSoLuongSPTrongGioHang);
            txtSoLuongSPTrongGioHang = itemView.findViewById(R.id.txtSoLuongSPTrongGioHang);

        }
    }

    @Override
    public ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang, parent, false);
        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);

        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(final ViewHolderGioHang holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);

        holder.txtTieuDeGioHang.setText(sanPham.getTenSanPham());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGia());
        holder.txtGiaTienGioHang.setText(gia + " VNĐ ");

        byte[] hinhSanPham = sanPham.getHinhgiohang();
        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(hinhSanPham, 0, hinhSanPham.length);
        holder.imHinhSanPhamGioHang.setImageBitmap(bitmapHinhGioHang);

        holder.imXoaSanPhamGioHang.setTag(sanPham.getMaSP());
        holder.imXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.moKetNoiSQL(context);
                modelGioHang.xoaSanPhamTrongGioHang((int) v.getTag());
                sanPhamList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.txtSoLuongSPTrongGioHang.setText(String.valueOf(sanPham.getSoLuong()));
        holder.imTangSoLuongSPTrongGioHang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSPTrongGioHang.getText().toString());
                soluong++;
                holder.txtSoLuongSPTrongGioHang.setText(String.valueOf(soluong));
                modelGioHang.capNhatSoLuongSanPhamGioHang(sanPham.getMaSP(), soluong);
            }
        });
        holder.imGiamSoLuongSPTrongGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSPTrongGioHang.getText().toString());
                soluong--;
                if (soluong >= 1) {
                    holder.txtSoLuongSPTrongGioHang.setText(String.valueOf(soluong));
                    modelGioHang.capNhatSoLuongSanPhamGioHang(sanPham.getMaSP(), soluong);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
