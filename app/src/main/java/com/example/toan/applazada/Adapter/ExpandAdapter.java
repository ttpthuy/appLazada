package com.example.toan.applazada.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.toan.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.toan.applazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.toan.applazada.R;

import java.util.ArrayList;
import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhams;
    ViewHolderMenu viewHolderMenu;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams) {
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
        int count = loaiSanPhams.size();

        for (int i = 0; i < count; i++) {
            int maloaisp = loaiSanPhams.get(i).getMALOAISP();
            loaiSanPhams.get(i).setListCon(xuLyJSONMenu.layLoaiSanPhamTheoMaLoai(maloaisp));
        }

    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if (loaiSanPhams.get(vitriGroupCha).getListCon().size() != 0) {
            return 1; // Mỗi group cha chỉ có một group con duy nhất mà thôi
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    public class ViewHolderMenu{
        TextView txtTenLoaiSP;
        ImageView hinhMenu;
    }

    // Tao giao dien cho group cha
    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {

        View viewGroupCha = view;

        if(viewGroupCha == null){

            viewHolderMenu  = new ViewHolderMenu();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha, viewGroup, false);
            viewHolderMenu.txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
           viewHolderMenu.hinhMenu = viewGroupCha.findViewById(R.id.imMenuPlus);

           viewGroupCha.setTag(viewHolderMenu);
        }else{
            viewHolderMenu = (ViewHolderMenu) viewGroupCha.getTag();
        }


        viewHolderMenu.txtTenLoaiSP.setText(loaiSanPhams.get(vitriGroupCha).getTENLOAISP());

        int demsanphamcon = loaiSanPhams.get(vitriGroupCha).getListCon().size();

        if (demsanphamcon > 0) {
            viewHolderMenu.hinhMenu.setVisibility(View.VISIBLE);
        } else {
            viewHolderMenu.hinhMenu.setVisibility(View.INVISIBLE);
        }

        if (isExpanded) {
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.ic_remove_black_24dp);
            viewGroupCha.setBackgroundResource(R.color.colorGray);
        } else {
            viewHolderMenu.hinhMenu.setImageResource(R.drawable.ic_add_black_24dp);
            viewGroupCha.setBackgroundResource(R.color.colorWhite);
        }

      viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
              Log.d("maloaisp", loaiSanPhams.get(vitriGroupCha).getTENLOAISP() + "-" + loaiSanPhams.get(vitriGroupCha).getMALOAISP());
              return false;
          }
      });

        return viewGroupCha;
    }

    // Tao giao dien cho group con
    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
        //      LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //      View viewGroupCon = layoutInflater.inflate(R.layout.custom_layout_group_con, viewGroup, false);
        //      ExpandableListView expandableListView = viewGroupCon.findViewById(R.id.epMenuCon);
        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpandAdapter secondAdapter = new ExpandAdapter(context, loaiSanPhams.get(vitriGroupCha).getListCon());
        secondExpanable.setAdapter(secondAdapter);
        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpanable;
    }

    // Lấy kích thước cho menu con được chuẩn xác nhất
    // Lớp này giúp xác định chiều rộng và chiều cao động cho group con
    public class SecondExpanable extends ExpandableListView {

        public SecondExpanable(Context context) {
            super(context);
        }


        @Override

        // PT Cho phép custom lại chiều rộng và chiều cao cho bất kỳ ExpandableListView hay bất kỳ view nào
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);

            int width = size.x;
            int height = size.y;
            Log.d("Size", width + " - " + height);

            // widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


//    public class SecondAdapter extends BaseExpandableListAdapter {
//
//        List<LoaiSanPham> listCon;
//
//        public SecondAdapter(List<LoaiSanPham> listCon) {
//            this.listCon = listCon;
//
//            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
//            int count = listCon.size();
//
//            for (int i = 0; i < count; i++) {
//                int maloaisp = listCon.get(i).getMALOAISP();
//                listCon.get(i).setListCon(xuLyJSONMenu.layLoaiSanPhamTheoMaLoai(maloaisp));
//            }
//        }
//
//        @Override
//        public int getGroupCount() {
//            return listCon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getListCon().size();
//        }
//
//        @Override
//        public Object getGroup(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha);
//        }
//
//        @Override
//        public Object getChild(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon);
//        }
//
//        @Override
//        public long getGroupId(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        // Tao giao dien cho group cha
//        @Override
//        public View getGroupView(int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {
//
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//            View viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha, viewGroup, false);
//            TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
//            txtTenLoaiSP.setText(listCon.get(vitriGroupCha).getTENLOAISP());
//
//            return viewGroupCha;
//        }
//
//        // Tao giao dien cho group con
//        @Override
//        public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
//
//            TextView tv = new TextView(context);
//            tv.setText(listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getTENLOAISP());
//            tv.setPadding(15,5,5,5);
//           //  tv.setBackgroundColor(Color.YELLOW);
//            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
////            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
////            View viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha, viewGroup, false);
////            TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
////            txtTenLoaiSP.setText(listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getTENLOAISP());
//
//            return tv;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return false;
//        }
//    }
}
