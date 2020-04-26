package com.csquare.sampleapp.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.csquare.sampleapp.data.db.AppDatabase;
import com.csquare.sampleapp.data.db.UserDao;
import com.csquare.sampleapp.model.Datum;
import com.csquare.sampleapp.model.UserList;
import com.csquare.sampleapp.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListRepository {

    private Context mContext;
    private UserDao mUserDao;
    private int mPageCount;
    private List<Datum> mDatumList;
    private MutableLiveData<List<Datum>> mDatumLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mUserInfoCallStatus = new MutableLiveData<>();
    private Executor mExecutor;

    public UserListRepository(Context context) {
        mContext = context;
        mDatumList = new ArrayList<>();
        mExecutor = Executors.newSingleThreadExecutor();
        mUserDao = AppDatabase.getAppDatabase(context).userDao();
        mPageCount = 1;
    }

    public void fetchUserList(int pageNo){
        NetworkUtils.getAPIService().getUsers(pageNo).enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                if(userList != null){
                    if(mPageCount < userList.getTotalPages()) {
                        mDatumList.addAll(userList.getData());
                        mPageCount++;
                        fetchUserList(mPageCount);
                    }else {
                        mDatumList.addAll(userList.getData());
                        mExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                mUserDao.insert(mDatumList);
                                mUserInfoCallStatus.postValue(true);
                            }
                        });
                    }
                }else {
                    mUserInfoCallStatus.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                mUserInfoCallStatus.postValue(false);
            }
        });
    }

    public LiveData<List<Datum>> getUserList(){
        return mDatumLiveData;
    }

    public LiveData<Boolean> getUserInfoCallStatus(){
        return mUserInfoCallStatus;
    }

    public void fetchUserListFromDb() {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mDatumLiveData.postValue(mUserDao.getAllUser());
            }
        });
    }
}
