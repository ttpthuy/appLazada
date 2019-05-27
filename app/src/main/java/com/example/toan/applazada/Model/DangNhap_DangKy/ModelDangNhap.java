package com.example.toan.applazada.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;

import com.example.toan.applazada.ConnectInternet.DownloadJSON;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken layTokenFacebookHienTai() {

        accessTokenTracker = new AccessTokenTracker() {

            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        return accessToken;
    }

    public String layCacheDangNhap(Context context) {
        SharedPreferences cacheDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        String tennv = cacheDangNhap.getString("tennv", "");

        return tennv;
    }

    public void capNhatCacheDangNhap(Context context, String tennv) {
        SharedPreferences cacheDangNhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cacheDangNhap.edit();
        editor.putString("tennv", tennv);
        editor.commit(); // luu vao file sharereferences
    }

    public boolean kiemTraDangNhap(Context context, String tendangnhap, String matkhau) {
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "kiemTraDangNhap");

        HashMap<String, String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("tendangnhap", tendangnhap);

        HashMap<String, String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("matkhau", matkhau);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);

            String jsonKetqua = jsonObject.getString("ketqua");
            if (jsonKetqua.equals("true")) {
                kiemtra = true;
                String tennv = jsonObject.getString("tennv");

                capNhatCacheDangNhap(context, tennv);


            } else {
                kiemtra = false;
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kiemtra;
    }

    public void huyToken() {
        accessTokenTracker.stopTracking();
    }

    public GoogleSignInClient layGoogleSignInClient(Context context) {
        GoogleSignInClient mGoogleSignInClient;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
        return mGoogleSignInClient;
    }

//    public GoogleSignInClient layThongTinDangNhapGoogle(Context context){
//       // OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
//
////        if(opr.isDone()){
////            return opr.get();
////        }
////        else{
////            return null;
////        }
//        if(account.getAccount()!=null){
//            return account.;
//        }else{
//            return null;
//        }
//    }

}
