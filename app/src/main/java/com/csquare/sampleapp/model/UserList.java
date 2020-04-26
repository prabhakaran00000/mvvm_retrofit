package com.csquare.sampleapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserList {

    @SerializedName("page")
    @Expose
    private int mPage;
    @SerializedName("per_page")
    @Expose
    private int mPerPage;
    @SerializedName("total")
    @Expose
    private int mTotal;
    @SerializedName("total_pages")
    @Expose
    private int mTotalPages;
    @SerializedName("data")
    @Expose
    private List<Datum> mData = null;


    public int getPage() {
        return mPage;
    }

    public void setPage(int mPage) {
        this.mPage = mPage;
    }

    public int getPerPage() {
        return mPerPage;
    }

    public void setPerPage(int mPerPage) {
        this.mPerPage = mPerPage;
    }

    public int getTotal() {
        return mTotal;
    }

    public void setTotal(int mTotal) {
        this.mTotal = mTotal;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int mTotalPages) {
        this.mTotalPages = mTotalPages;
    }

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> mData) {
        this.mData = mData;
    }
}