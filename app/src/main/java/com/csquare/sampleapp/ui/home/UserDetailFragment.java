package com.csquare.sampleapp.ui.home;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.csquare.sampleapp.R;
import com.csquare.sampleapp.databinding.FragmentUserDetailsBinding;
import com.csquare.sampleapp.model.Datum;

public class UserDetailFragment extends Fragment {

    private Datum mUserDetail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        FragmentUserDetailsBinding mFragmentUserDetailsBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_user_details, container, false);
        mUserDetail = (Datum) getArguments().getSerializable("UserDetail");
        mFragmentUserDetailsBinding.setUserDetail(mUserDetail);
        Glide.with(getActivity())
                .load(mUserDetail.getAvatar())
                .fitCenter()
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable>
                            target, boolean isFirstResource) {
                        mFragmentUserDetailsBinding.loading.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable>
                            target, DataSource dataSource, boolean isFirstResource) {
                        mFragmentUserDetailsBinding.loading.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(mFragmentUserDetailsBinding.imageUser);
        return mFragmentUserDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
