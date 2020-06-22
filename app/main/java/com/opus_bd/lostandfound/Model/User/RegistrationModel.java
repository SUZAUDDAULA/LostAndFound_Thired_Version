package com.opus_bd.lostandfound.Model.User;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationModel {

    @SerializedName("Citizenship")
    @Expose
    private String citizenship;
    @SerializedName("NationalIdentityType")
    @Expose
    private String nationalIdentityType;
    @SerializedName("NationalIdentityNo")
    @Expose
    private String nationalIdentityNo;
    @SerializedName("AddressType")
    @Expose
    private String addressType;
    @SerializedName("PhoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("ConfirmPassword")
    @Expose
    private String confirmPassword;


    @SerializedName("otpCode")
    @Expose
    private String otpCode;

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getNationalIdentityType() {
        return nationalIdentityType;
    }

    public void setNationalIdentityType(String nationalIdentityType) {
        this.nationalIdentityType = nationalIdentityType;
    }

    public String getNationalIdentityNo() {
        return nationalIdentityNo;
    }

    public void setNationalIdentityNo(String nationalIdentityNo) {
        this.nationalIdentityNo = nationalIdentityNo;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getOtpCode() {
        return otpCode;
    }

    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    @Override
    public String toString() {
        return "RegistrationModel{" +
                "citizenship='" + citizenship + '\'' +
                ", nationalIdentityType='" + nationalIdentityType + '\'' +
                ", nationalIdentityNo='" + nationalIdentityNo + '\'' +
                ", addressType='" + addressType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
