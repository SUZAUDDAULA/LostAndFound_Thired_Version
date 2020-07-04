package com.opus_bd.lostandfound.Model.User;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileModel {
    @SerializedName("Citizenship")
    @Expose
    private String Citizenship;
    @SerializedName("NationalIdentityType")
    @Expose
    private Integer NationalIdentityType;
    @SerializedName("NationalIdentityNo")
    @Expose
    private String NationalIdentityNo;
    @SerializedName("AddressType")
    @Expose
    private Integer AddressType;
    @SerializedName("PhoneNumber")
    @Expose
    private String PhoneNumber;
    @SerializedName("UserName")
    @Expose
    private String UserName;
    @SerializedName("Email")
    @Expose
    private String Email;
    @SerializedName("Password")
    @Expose
    private String Password;
    @SerializedName("ConfirmPassword")
    @Expose
    private String ConfirmPassword;

    @SerializedName("otpCode")
    @Expose
    private String otpCode;
    @SerializedName("FullName")
    @Expose
    private String FullName;
    @SerializedName("userFrom")
    @Expose
    private String userFrom;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;

    public UserProfileModel(String citizenship, Integer nationalIdentityType, String nationalIdentityNo, Integer addressType, String phoneNumber, String userName, String email, String password, String confirmPassword, String otpCode, String fullName, String userFrom, String imagePath) {
        Citizenship = citizenship;
        NationalIdentityType = nationalIdentityType;
        NationalIdentityNo = nationalIdentityNo;
        AddressType = addressType;
        PhoneNumber = phoneNumber;
        UserName = userName;
        Email = email;
        Password = password;
        ConfirmPassword = confirmPassword;
        this.otpCode = otpCode;
        FullName = fullName;
        this.userFrom = userFrom;
        this.imagePath = imagePath;
    }

    public String getCitizenship() {
        return Citizenship;
    }

    public void setCitizenship(String Citizenship) {
        this.Citizenship = Citizenship;
    }

    public Integer getNationalIdentityType() {
        return NationalIdentityType;
    }

    public void setNationalIdentityType(Integer NationalIdentityType) {
        this.NationalIdentityType = NationalIdentityType;
    }

    public String getNationalIdentityNo() {
        return NationalIdentityNo;
    }

    public void setNationalIdentityNo(String NationalIdentityNo) {
        this.NationalIdentityNo = NationalIdentityNo;
    }

    public Integer getAddressType() {
        return AddressType;
    }

    public void setAddressType(Integer AddressType) {
        this.AddressType = AddressType;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = UserName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = Password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String ConfirmPassword) {
        this.ConfirmPassword = ConfirmPassword;
    }

    public String getOtpCode() {
        return otpCode;
    }
    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    public String getFullName() {
        return FullName;
    }
    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getUserFrom() {
        return userFrom;
    }
    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "UserProfileModel{" +
                "Citizenship='" + Citizenship + '\'' +
                ", NationalIdentityType=" + NationalIdentityType +
                ", NationalIdentityNo='" + NationalIdentityNo + '\'' +
                ", AddressType=" + AddressType +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", UserName='" + UserName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", ConfirmPassword='" + ConfirmPassword + '\'' +
                ", otpCode='" + otpCode + '\'' +
                ", FullName='" + FullName + '\'' +
                ", userFrom='" + userFrom + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
