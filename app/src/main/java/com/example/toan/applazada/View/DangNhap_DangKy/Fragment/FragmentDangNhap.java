package com.example.toan.applazada.View.DangNhap_DangKy.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.toan.applazada.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.toan.applazada.R;
import com.example.toan.applazada.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

public class FragmentDangNhap extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    Button btnDangNhapFacebook, btnDangNhapGoogle, btnDangNhap;
    CallbackManager callbackManager;
    // GoogleApiClient mGoogleApiClient;
    GoogleSignInClient mGoogleSignInClient;
    public static int SIGN_IN_GOOGLE_PLUS = 111;
    ProgressDialog progressDialog;
    ModelDangNhap modelDangNhap;
    EditText edTenDangNhap, edMatKhau;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap, container, false);
//          CODE trên trang deverloper google - Vì thiết kế theo mô hình MVP nên chuyển code này qua phần Model
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        modelDangNhap = new ModelDangNhap();
        mGoogleSignInClient = modelDangNhap.layGoogleSignInClient(getContext());

//        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
//                .enableAutoManage(getActivity(), this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API)
//                .build();

        FacebookSdk.sdkInitialize(getContext().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        edTenDangNhap = view.findViewById(R.id.edDiaChiEmailDangNhap);
        edMatKhau = view.findViewById(R.id.edMatKhauDangNhap);

        btnDangNhapFacebook = view.findViewById(R.id.btnDangNhapFacebook);
        btnDangNhapGoogle = view.findViewById(R.id.btnDangNhapGoogle);
        btnDangNhap = view.findViewById(R.id.btnDangNhap);

        btnDangNhapGoogle.setOnClickListener(this);
        btnDangNhapFacebook.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);

        // Đoạn code do facebook cung cấp dể lấy KeyHash
//        try {
//            PackageInfo info = getActivity().getPackageManager().getPackageInfo("com.example.toan.applazada", PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        return view;
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Đang đăng nhập...");

            progressDialog.show();
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.btnDangNhapFacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btnDangNhapGoogle:
//                Intent iGooglePlus = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//                startActivityForResult(iGooglePlus, SIGN_IN_GOOGLE_PLUS);
                showProgressDialog();
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, SIGN_IN_GOOGLE_PLUS);
                break;
            case R.id.btnDangNhap:
                String tendangnhap = edTenDangNhap.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                Log.d("dangnhap", tendangnhap + "-" + matkhau);
              boolean kiemtra =  modelDangNhap.kiemTraDangNhap(getActivity(),tendangnhap, matkhau);
              if(kiemtra){
                  Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                  startActivity(iTrangChu);
              }else{
                  Toast.makeText(getActivity(), "Tên đăng nhập hoặc mât khẩu không chính xác!", Toast.LENGTH_SHORT).show();
              }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_GOOGLE_PLUS) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            Log.d("Google", result.getSignInAccount().getEmail());
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (task.isSuccessful()) {
                progressDialog.cancel();
                Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                startActivity(iTrangChu);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        progressDialog.hide();
    }
}
