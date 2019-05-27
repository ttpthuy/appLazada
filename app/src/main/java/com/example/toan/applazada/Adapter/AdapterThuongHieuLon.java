package com.example.toan.applazada.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.toan.applazada.Model.ObjectClass.ThuongHieu;
import com.example.toan.applazada.R;
import com.example.toan.applazada.View.HienThiSanPhamTheoDanhMuc.HienThiSanPhamTheoDanhMucActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolderThuongHieu> {

    Context context;
    List<ThuongHieu> thuongHieus;
    boolean kiemtra;

    public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieus, boolean kiemtra) {
        this.context = context;
        this.thuongHieus = thuongHieus;
        this.kiemtra = kiemtra;
    }


    public class ViewHolderThuongHieu extends RecyclerView.ViewHolder {
        TextView txtTieuDeThuongHieu;
        ImageView imHinhThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolderThuongHieu(View itemView) {
            super(itemView);
            txtTieuDeThuongHieu = itemView.findViewById(R.id.txtTieuDeThuongHieuLonDienTu);
            imHinhThuongHieu = itemView.findViewById(R.id.imHinhThuongHieuLonDienTu);
            progressBar = itemView.findViewById(R.id.progress_bar_download);
            linearLayout = itemView.findViewById(R.id.lnthuonghieulon);
        }
    }

    @Override
    public ViewHolderThuongHieu onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_thuonghieulon, parent, false);

        ViewHolderThuongHieu viewHolder = new ViewHolderThuongHieu(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderThuongHieu holder, int position) {
        final ThuongHieu thuongHieu = thuongHieus.get(position);
        holder.txtTieuDeThuongHieu.setText(thuongHieu.getTenThuongHieu());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHienThiSanPhamTheoDanhMuc = new Intent(context, HienThiSanPhamTheoDanhMucActivity.class);

                iHienThiSanPhamTheoDanhMuc.putExtra("MALOAI", thuongHieu.getMaThuongHieu());
                iHienThiSanPhamTheoDanhMuc.putExtra("TENLOAI", thuongHieu.getTenThuongHieu());
                iHienThiSanPhamTheoDanhMuc.putExtra("KIEMTRA", kiemtra);

                context.startActivity(iHienThiSanPhamTheoDanhMuc);
                Log.d("masp", thuongHieu.getMaThuongHieu()+"-" +thuongHieu.getTenThuongHieu());
            }
        });
        Picasso.get().load(thuongHieu.getHinhThuongHieu()).resize(110, 110).into(holder.imHinhThuongHieu, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }
}
