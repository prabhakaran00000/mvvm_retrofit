package com.csquare.sampleapp.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.csquare.sampleapp.R;
import com.csquare.sampleapp.databinding.RowUserListBinding;
import com.csquare.sampleapp.model.Datum;
import com.csquare.sampleapp.ui.home.UserDetailFragment;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListHolder> {

    private List<Datum> mDatumList;
    private Context mContext;

    public UserListAdapter(List<Datum> datumList, Context context) {
        mDatumList = datumList;
        mContext = context;
    }

    @NonNull
    @Override
    public UserListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RowUserListBinding rowUserListBinding = DataBindingUtil.inflate(layoutInflater,
                R.layout.row_user_list, parent, false);
        return new UserListHolder(rowUserListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListHolder holder, int position) {
        Datum datum = mDatumList.get(position);
        holder.bind(datum);
    }

    @Override
    public int getItemCount() {
        return mDatumList.size();
    }

    public class UserListHolder extends RecyclerView.ViewHolder {

        private RowUserListBinding mRowUserListBinding;

        public UserListHolder(@NonNull RowUserListBinding rowUserListBinding) {
            super(rowUserListBinding.getRoot());
            mRowUserListBinding = rowUserListBinding;

            mRowUserListBinding.cardviewRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    UserDetailFragment userDetailFragment = new UserDetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("UserDetail",mDatumList.get(getAdapterPosition()));
                    userDetailFragment.setArguments(bundle);
                    activity.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frame_container, userDetailFragment)
                            .addToBackStack(null)
                            .commit();
                }
            });
        }

        public void bind(Datum userInfo) {
            mRowUserListBinding.setUserInfo(userInfo);
        }
    }

    public void refreshData(List<Datum> datumList){
        mDatumList = datumList;
        notifyDataSetChanged();
    }
}
