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
import android.widget.HorizontalScrollView;

import com.example.toan.applazada.Adapter.AdapterNoiBat;
import com.example.toan.applazada.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentNoiBat extends Fragment {
    RecyclerView recyclerView;
    AdapterNoiBat adapterNoiBat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat, container, false);
        recyclerView = view.findViewById(R.id.recycleNoiBat);
        List<String> dulieu = new ArrayList<>();
        for (int i = 0; i < 70; i++) {
            String noibat = "noibat " + i;
            dulieu.add(noibat);
        }
      //   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); // Hiển thị theo chiều dọc
      //  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
     RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        adapterNoiBat = new AdapterNoiBat(getActivity(), dulieu);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNoiBat);
        adapterNoiBat.notifyDataSetChanged();
        return view;
    }
}
