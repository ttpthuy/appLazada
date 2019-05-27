package com.example.toan.applazada.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.toan.applazada.Model.ObjectClass.DanhGia;
import com.example.toan.applazada.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia> {

    List<DanhGia> danhGiaList;
    int limit;
    Context context;

    public AdapterDanhGia(List<DanhGia> danhGiaList, int limit, Context context) {
        this.danhGiaList = danhGiaList;
        this.limit = limit;
        this.context = context;
    }


    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {

        TextView txtTieuDeDanhGia, txtNoiDungDanhGia, txtDuocDanhGiaBoi;
        RatingBar rbDanhGia;

        public ViewHolderDanhGia(View itemView) {
            super(itemView);
            txtTieuDeDanhGia = itemView.findViewById(R.id.txtTieuDeDanhGia);
            txtNoiDungDanhGia = itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtDuocDanhGiaBoi = itemView.findViewById(R.id.txtDuocDangBoi);
            rbDanhGia = itemView.findViewById(R.id.rbDanhGia);

        }
    }

    @Override
    public AdapterDanhGia.ViewHolderDanhGia onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_danhgiachitiet, parent, false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);
        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(AdapterDanhGia.ViewHolderDanhGia holder, int position) {
        DanhGia danhGia = danhGiaList.get(position);
        holder.txtTieuDeDanhGia.setText(danhGia.getTieuDe());
        holder.txtNoiDungDanhGia.setText(danhGia.getNoiDung());
        holder.txtDuocDanhGiaBoi.setText("Được đánh giá bởi: " + danhGia.getTenThietBi() + " vào ngày: " + danhGia.getNgayDanhGia());
        holder.rbDanhGia.setRating(danhGia.getSoSao());
    }

    @Override
    public int getItemCount() {
        int dong =0;
        if(danhGiaList.size() < limit){
            dong = danhGiaList.size();
        }
        else{
            if(limit!= 0){
                dong =  limit;
            }else{
                dong = danhGiaList.size();
            }
        }
        return dong;
    }
}
