package com.csquare.sampleapp.ui.login;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.app.Application;
import android.content.Context;

import com.csquare.sampleapp.R;
import com.csquare.sampleapp.model.LoginUser;
import com.csquare.sampleapp.data.LoginRepository;
import com.csquare.sampleapp.utils.AppUtils;

public class LoginViewModel extends AndroidViewModel {

    private final LoginRepository mLoginRepository;
    private LiveData<LoginUser> mUserLiveData;
    private MutableLiveData<Boolean> mProgressLiveData = new MutableLiveData<>();
    public final ObservableField<String> errorUserName = new ObservableField<>();
    public final ObservableField<String> errorPassword = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    private Context mContext;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mLoginRepository = new LoginRepository(application);
        mUserLiveData = mLoginRepository.getUser();
        mContext = application;
        email.set("eve.holt@reqres.in");
        password.set("cityslicka");
    }

    public LiveData<LoginUser> getLoginUser() {
        return mUserLiveData;
    }

    public LiveData<Boolean> getProgress() {
        return mProgressLiveData;
    }

    public void validateLogin() {
        if (email.get() == null || email.get().isEmpty()) {
            errorUserName.set(mContext.getString(R.string.username_empty));
        } else if (!AppUtils.isEmailValid(email.get())) {
            errorUserName.set(mContext.getString(R.string.invalid_username));
        } else if (password.get() == null || password.get().isEmpty()) {
            errorPassword.set(mContext.getString(R.string.password_empty));
        } else if (password.get().length() < 5) {
            errorPassword.set(mContext.getString(R.string.invalid_password));
        } else {
            errorUserName.set("");
            errorPassword.set("");
            mProgressLiveData.setValue(true);
            LoginUser loginUser = new LoginUser();
            loginUser.setEmail(email.get());
            loginUser.setPassword(password.get());
            mLoginRepository.login(loginUser);
        }
    }
}
