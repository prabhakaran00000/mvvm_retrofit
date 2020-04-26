package com.csquare.sampleapp.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.csquare.sampleapp.R;
import com.csquare.sampleapp.model.LoginUser;
import com.csquare.sampleapp.utils.NetworkUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private Context mContext;
    private MutableLiveData<LoginUser> mLoginUserMutableLiveData = new MutableLiveData<>();
    public LoginRepository(Context context) {
        mContext = context;
    }

    public void login(LoginUser loginUser){
        NetworkUtils.getAPIService().getLogin(loginUser).enqueue(new Callback<LoginUser>() {
            @Override
            public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                LoginUser loginResponse = response.body();
                mLoginUserMutableLiveData.setValue(loginResponse);
            }

            @Override
            public void onFailure(Call<LoginUser> call, Throwable t) {
                LoginUser loginUser = new LoginUser();
                loginUser.setError(mContext.getString(R.string.login_auth_failed));
                mLoginUserMutableLiveData.setValue(loginUser);
            }
        });
    }

    public LiveData<LoginUser> getUser() {
        return mLoginUserMutableLiveData;
    }

}
