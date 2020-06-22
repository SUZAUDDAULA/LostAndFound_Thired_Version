
package com.opus_bd.lostandfound.Model.OthersItem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComputerInfo {
    private String gdFor;
    @SerializedName("gdDate")
    @Expose
    private String gdDate;
    @SerializedName("gdNumber")
    @Expose
    private String gdNumber;
    @SerializedName("productTypeId")
    @Expose
    private Integer productTypeId;
    @SerializedName("gDTypeId")
    @Expose
    private Integer gDTypeId;
    @SerializedName("documentTypeId")
    @Expose
    private Integer documentTypeId;
    @SerializedName("documentDescription")
    @Expose
    private String documentDescription;

    @SerializedName("nationalIdentityTypeId")
    @Expose
    private Integer nationalIdentityTypeId;
    @SerializedName("identityNo")
    @Expose
    private String identityNo;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("brandTypeId")
    @Expose
    private Integer brandTypeId;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("serialNo")
    @Expose
    private String serialNo;
    @SerializedName("emcProductId")
    @Expose
    private String emcProductId;
    @SerializedName("serviceCode")
    @Expose
    private String serviceCode;
    @SerializedName("colorId")
    @Expose
    private Integer colorId;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("identificationMark")
    @Expose
    private String identificationMark;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("thanaId")
    @Expose
    private Integer thanaId;
    @SerializedName("village")
    @Expose
    private String village;
    @SerializedName("addressDetails")
    @Expose
    private String addressDetails;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    public String getGdFor() {
        return gdFor;
    }

    public void setGdFor(String gdFor) {
        this.gdFor = gdFor;
    }

    public String getGdDate() {
        return gdDate;
    }

    public void setGdDate(String gdDate) {
        this.gdDate = gdDate;
    }

    public String getGdNumber() {
        return gdNumber;
    }

    public void setGdNumber(String gdNumber) {
        this.gdNumber = gdNumber;
    }

    public Integer getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getGDTypeId() {
        return gDTypeId;
    }

    public void setGDTypeId(Integer gDTypeId) {
        this.gDTypeId = gDTypeId;
    }

    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public Integer getNationalIdentityTypeId() {
        return nationalIdentityTypeId;
    }

    public void setNationalIdentityTypeId(Integer nationalIdentityTypeId) {
        this.nationalIdentityTypeId = nationalIdentityTypeId;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getBrandTypeId() {
        return brandTypeId;
    }

    public void setBrandTypeId(Integer brandTypeId) {
        this.brandTypeId = brandTypeId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getEmcProductId() {
        return emcProductId;
    }

    public void setEmcProductId(String emcProductId) {
        this.emcProductId = emcProductId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIdentificationMark() {
        return identificationMark;
    }

    public void setIdentificationMark(String identificationMark) {
        this.identificationMark = identificationMark;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getThanaId() {
        return thanaId;
    }

    public void setThanaId(Integer thanaId) {
        this.thanaId = thanaId;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ComputerInfo{" +
                "userName='" + userName + '\'' +
                ", brandTypeId=" + brandTypeId +
                ", model='" + model + '\'' +
                ", serialNo='" + serialNo + '\'' +
                ", emcProductId='" + emcProductId + '\'' +
                ", serviceCode='" + serviceCode + '\'' +
                ", colorId=" + colorId +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", identificationMark='" + identificationMark + '\'' +
                ", districtId=" + districtId +
                ", thanaId=" + thanaId +
                ", village='" + village + '\'' +
                ", addressDetails='" + addressDetails + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
