package com.example.toan.applazada.View.TrangChu;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.toan.applazada.Adapter.ExpandAdapter;
import com.example.toan.applazada.Adapter.ViewPagerAdapter;
import com.example.toan.applazada.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.toan.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.toan.applazada.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.toan.applazada.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import com.example.toan.applazada.R;
import com.example.toan.applazada.View.DangNhap_DangKy.DangNhapActivity;
import com.example.toan.applazada.View.GioHang.GioHangActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, AppBarLayout.OnOffsetChangedListener {

    public static final String SERVER_NAME = "http://10.0.3.2/weblazada/loaisanpham.php";
    public static final String SERVER = "http://10.0.3.2/weblazada";
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ExpandableListView expandableListView; // 122118
    PresenterLogicXuLyMenu logicXuLyMenu;
    String tenNguoiDung = "";
    AccessToken accessToken;
    Menu menu;
    ModelDangNhap modelDangNhap;
    MenuItem itDangNhap, menuItDangXuat;
    GoogleSignInResult mGoogleSignInResult;
    //122018
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    GoogleSignInAccount account;
    GoogleSignInClient mGoogleSignInClient;
    TextView txtGioHang;
    //301218
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    boolean onPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());


        super.onCreate(savedInstanceState);
        setContentView(R.layout.trangchu_layout);

        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.drawerLayout);  //122018
        expandableListView = findViewById(R.id.epMenu); // 122118
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);//301218

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //122018 Khai báo chuổi đóng và chuổi mở cho menu đa cấp bên trái và chứa drawer layout
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle); // phải add sau toolbar


        //122018
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState(); // đồng bộ


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        modelDangNhap = new ModelDangNhap();
        logicXuLyMenu.layTokenNguoiDungFacebook();// Phải đặt trước phương thức setContentView

        logicXuLyMenu.layDanhSachMenu();

        // modelDangNhap.layGoogleSignInClient(this).asGoogleApiClient().connect();
        //idToken= modelDangNhap.layThongTinDangNhapGoogle(this);
        //  mGoogleSignInResult.getSignInAccount();
        appBarLayout.addOnOffsetChangedListener(this);

    }

    // đê activity hiển thị ra menu
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;

        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        //View giaoDienCustomGioHang = MenuItemCompat.getActionView();
        View giaoDienCustomGioHang = iGioHang.getActionView(); // Tự sửa theo trên mạng
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.demSoLuongSanPhamCoTrongGioHang(this)));

        itDangNhap = menu.findItem(R.id.itDangnhap);
        menuItDangXuat = menu.findItem(R.id.itDangXuat);
        accessToken = logicXuLyMenu.layTokenNguoiDungFacebook();

        account = GoogleSignIn.getLastSignedInAccount(this);
        mGoogleSignInClient = GoogleSignIn.getClient(this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        if (accessToken != null) {

            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tenNguoiDung = object.getString("name");
                        itDangNhap.setTitle(tenNguoiDung);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "name");

            graphRequest.setParameters(parameters);
            graphRequest.executeAsync();
        }

        String tennv = modelDangNhap.layCacheDangNhap(this);
        if (!tennv.equals("")) {
            itDangNhap.setTitle(tennv);
        }

        if (account != null) {
            itDangNhap.setTitle(account.getDisplayName());
        }
        if (accessToken != null || account != null || !tennv.equals("")) {
            menuItDangXuat.setVisible(true);
        }
        return true;
    }


    // Đăng ký sự kiện click cho menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        switch (id) {
            case R.id.itDangnhap:
                if (accessToken == null && account == null && modelDangNhap.layCacheDangNhap(this).equals("")) {
                    Intent iDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(iDangNhap);
                }
                break;
            case R.id.itDangXuat:
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if (account != null) {
                    mGoogleSignInClient.signOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if (!modelDangNhap.layCacheDangNhap(this).equals("")) {
                    modelDangNhap.capNhatCacheDangNhap(this, "");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;

        }
        return true;
    }


    @Override
    public void hienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        ExpandAdapter expandAdapter = new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        // Log.d("KIEM TRA", collapsingToolbarLayout.getHeight() + "__" + verticalOffset + "__" + ViewCompat.getMinimumHeight(collapsingToolbarLayout));
        if (collapsingToolbarLayout.getHeight() + verticalOffset <= 1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)) {
            // Mục đích nhân cho 1.5 là để cho ra một chiều cao lớn hơn đủ để che khuất đi cái ImageView
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            //linearLayout.setAlpha(0); // Để ẩn đi (cách ẩn đi nhanh)

            linearLayout.animate().alpha(0).setDuration(500);// Ẩn đi chầm chậm, mượt hơn nhờ animate
        } else {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            //linearLayout.setAlpha(1); // Để hiển thị lên lại
            linearLayout.animate().alpha(1).setDuration(500);// Ẩn đi chầm chậm, mượt hơn nhờ animate
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (onPause) {

            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.demSoLuongSanPhamCoTrongGioHang(this)));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
}
