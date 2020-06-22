package com.opus_bd.lostandfound.Model.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAuthModel {

    @SerializedName("jwt")
    @Expose
    private String jwt;
    @SerializedName("otpCode")
    @Expose
    private String otpCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("userInfo")
    @Expose
    private UserInfo userInfo;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

}
