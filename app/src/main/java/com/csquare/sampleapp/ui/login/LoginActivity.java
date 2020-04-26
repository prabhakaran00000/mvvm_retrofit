package com.csquare.sampleapp.ui.login;


import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import com.csquare.sampleapp.ui.home.HomeActivity;
import com.csquare.sampleapp.R;
import com.csquare.sampleapp.databinding.ActivityLoginBinding;
import com.csquare.sampleapp.model.LoginUser;
import com.csquare.sampleapp.utils.AppUtils;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mActivityLoginBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLoginBinding =  DataBindingUtil.setContentView(this, R.layout.activity_login);
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        mActivityLoginBinding.setLoginViewModel(mLoginViewModel);
        mLoginViewModel.getLoginUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(@Nullable LoginUser loginUser) {
                mActivityLoginBinding.loading.setVisibility(View.GONE);
                if(loginUser != null){
                    if(loginUser.getToken() != null && !loginUser.getToken().isEmpty()){
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }else {
                        Toast.makeText(LoginActivity.this, loginUser.getEmail(), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this, getString(R.string.login_auth_failed)
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });

        mLoginViewModel.getProgress().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    mActivityLoginBinding.loading.setVisibility(View.VISIBLE);
                }else {
                    mActivityLoginBinding.loading.setVisibility(View.GONE);
                }
            }
        });

        mActivityLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppUtils.isNetworkConnected()){
                    mLoginViewModel.validateLogin();
                }else {
                    Toast.makeText(LoginActivity.this, getString(R.string.error_internet)
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
