package com.example.toan.applazada.Adapter;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.toan.applazada.R;

import java.util.List;

public class AdapterNoiBat extends RecyclerView.Adapter<AdapterNoiBat.ViewHolder> {

    Context context;
    List<String> stringList;

    public AdapterNoiBat(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    // Chạy thứ 2
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtTieuDeNoiBat);
        }
    }

    // Chạy đầu tiên
    @Override
    public AdapterNoiBat.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycleview_noibat, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    // Chạy thứ ba
    @Override
    public void onBindViewHolder(AdapterNoiBat.ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
