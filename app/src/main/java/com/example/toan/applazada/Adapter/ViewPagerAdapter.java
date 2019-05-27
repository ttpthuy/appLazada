package com.example.toan.applazada.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.toan.applazada.View.TrangChu.Fragment.FragmentChuongTrinhKhuyenMai;
import com.example.toan.applazada.View.TrangChu.Fragment.FragmentDienTu;
import com.example.toan.applazada.View.TrangChu.Fragment.FragmentLamDep;
import com.example.toan.applazada.View.TrangChu.Fragment.FragmentMeVaBe;
import com.example.toan.applazada.View.TrangChu.Fragment.FragmentNhaCuaVaDoiSong;
import com.example.toan.applazada.View.TrangChu.Fragment.FragmentNoiBat;
import com.example.toan.applazada.View.TrangChu.Fragment.FragmentTheThaoVaDuLich;
import com.example.toan.applazada.View.TrangChu.Fragment.FragmentThoiTrang;
import com.example.toan.applazada.View.TrangChu.Fragment.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragmentList = new ArrayList<Fragment>();
    List<String> titlefragment = new ArrayList<String>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList.add(new FragmentNoiBat());
        fragmentList.add(new FragmentChuongTrinhKhuyenMai());
        fragmentList.add(new FragmentDienTu());
        fragmentList.add(new FragmentNhaCuaVaDoiSong());
        fragmentList.add(new FragmentMeVaBe());
        fragmentList.add(new FragmentLamDep());
        fragmentList.add(new FragmentThoiTrang());
        fragmentList.add(new FragmentTheThaoVaDuLich());
        fragmentList.add(new FragmentThuongHieu());

        titlefragment.add("Nổi bật");
        titlefragment.add("Chương trình khuyến mãi");
        titlefragment.add("Điện tử");
        titlefragment.add("Nhà cửa & đời sống");
        titlefragment.add("Mẹ & bé");
        titlefragment.add("Làm đẹp");
        titlefragment.add("Thời trang");
        titlefragment.add("Thể thao & du lịch");
        titlefragment.add("Thương hiệu");

    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlefragment.get(position);
    }
}
