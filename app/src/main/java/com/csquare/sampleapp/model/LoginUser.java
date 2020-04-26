package com.csquare.sampleapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUser{
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("password")
    @Expose
    private String mPassword;
    @SerializedName("token")
    @Expose
    private String mToken;
    @SerializedName("error")
    @Expose
    private String mError;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String mToken) {
        this.mToken = mToken;
    }

    public String getError() {
        return mError;
    }

    public void setError(String mError) {
        this.mError = mError;
    }
}
