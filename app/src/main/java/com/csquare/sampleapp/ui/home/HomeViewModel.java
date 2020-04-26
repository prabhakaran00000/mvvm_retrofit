package com.csquare.sampleapp.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.csquare.sampleapp.data.UserListRepository;
import com.csquare.sampleapp.model.Datum;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private UserListRepository mUserListRepository;
    private LiveData<List<Datum>> mDatumLiveData = new MutableLiveData<>();
    private LiveData<Boolean> mUserInfoCallStatus = new MutableLiveData<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
        mUserListRepository = new UserListRepository(application);
        mUserListRepository.fetchUserList(1);
        mDatumLiveData = mUserListRepository.getUserList();
        mUserInfoCallStatus = mUserListRepository.getUserInfoCallStatus();
    }

    public LiveData<List<Datum>> getUserListData(){
        return mDatumLiveData;
    }

    public LiveData<Boolean> getUserInfoCallStatus(){
        return mUserInfoCallStatus;
    }

    public void fetchUserListFromDb(){
        mUserListRepository.fetchUserListFromDb();
    }


}
