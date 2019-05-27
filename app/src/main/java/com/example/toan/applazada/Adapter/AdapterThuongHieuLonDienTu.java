package com.example.toan.applazada.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.toan.applazada.Model.ObjectClass.ThuongHieu;
import com.example.toan.applazada.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLonDienTu extends RecyclerView.Adapter<AdapterThuongHieuLonDienTu.ViewHolderthuongHieuLon> {

    Context context;
    List<ThuongHieu> thuongHieus;

    public AdapterThuongHieuLonDienTu(Context context, List<ThuongHieu> thuongHieus) {
        this.context = context;
        this.thuongHieus = thuongHieus;
    }

    public class ViewHolderthuongHieuLon extends RecyclerView.ViewHolder {
        ImageView imLogoThuongHieuLon;

        public ViewHolderthuongHieuLon(View itemView) {
            super(itemView);
            imLogoThuongHieuLon = itemView.findViewById(R.id.imLogoTopThuongHieuLon);

        }
    }

    @Override
    public AdapterThuongHieuLonDienTu.ViewHolderthuongHieuLon onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_recycler_topthuonghieulon_dientu, parent, false);

        ViewHolderthuongHieuLon viewHolderthuongHieuLon = new ViewHolderthuongHieuLon(view);
        return viewHolderthuongHieuLon;
    }

    @Override
    public void onBindViewHolder(AdapterThuongHieuLonDienTu.ViewHolderthuongHieuLon holder, int position) {
        ThuongHieu thuongHieu = thuongHieus.get(position);
        Picasso.get().load(thuongHieu.getHinhThuongHieu()).resize(120, 50).centerInside().into(holder.imLogoThuongHieuLon);
    }

    @Override
    public int getItemCount() {
        return thuongHieus.size();
    }
}
