package com.csquare.sampleapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.csquare.sampleapp.R;
import com.csquare.sampleapp.databinding.FragmentUserListBinding;
import com.csquare.sampleapp.model.Datum;
import com.csquare.sampleapp.ui.adapter.UserListAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment {

    private FragmentUserListBinding mFragmentUserListBinding;
    private UserListAdapter mUserListAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {

        mFragmentUserListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_list,
                container, false);
        mFragmentUserListBinding.setUserCount(0);
        RecyclerView recyclerView = mFragmentUserListBinding.recyclerView;
        mUserListAdapter = new UserListAdapter(new ArrayList<Datum>(),getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mUserListAdapter);
        return mFragmentUserListBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeViewModel homeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        homeViewModel.fetchUserListFromDb();
        homeViewModel.getUserListData().observe(getViewLifecycleOwnerLiveData().getValue(), new Observer<List<Datum>>() {
            @Override
            public void onChanged(List<Datum> datumList) {
                if(datumList != null){
                    mUserListAdapter.refreshData(datumList);
                    mFragmentUserListBinding.setUserCount(datumList.size());
                }
            }
        });
    }


}
