package com.csquare.sampleapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.csquare.sampleapp.R;
import com.csquare.sampleapp.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding activityHomeBinding  = DataBindingUtil
                .setContentView(this, R.layout.activity_home);
        activityHomeBinding.loading.setVisibility(View.VISIBLE);
        HomeViewModel homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getUserInfoCallStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                activityHomeBinding.loading.setVisibility(View.GONE);
                if(aBoolean){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(activityHomeBinding.frameContainer.getId(),new UserListFragment())
                            .commit();
                }else{
                    Toast.makeText(HomeActivity.this, getString(R.string.error_try_again), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
