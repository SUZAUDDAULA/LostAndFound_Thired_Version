
package com.opus_bd.lostandfound.Model.GDInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GDViewModel {

    @SerializedName("gdFor")
    @Expose
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

    @SerializedName("gDInformationId")
    @Expose
    private Integer gDInformationId;

    @SerializedName("vehicleId")
    @Expose
    private Integer vehicleId;
    @SerializedName("vehicleTypeId")
    @Expose
    private Integer vehicleTypeId;
    @SerializedName("vehicleBrandId")
    @Expose
    private Integer vehicleBrandId;
    @SerializedName("vehicleRegNo")
    @Expose
    private String vehicleRegNo;
    @SerializedName("regNoFirstPart")
    @Expose
    private String regNoFirstPart;
    @SerializedName("regNoSecondPart")
    @Expose
    private String regNoSecondPart;
    @SerializedName("regNoThiredPart")
    @Expose
    private String regNoThiredPart;
    @SerializedName("madeBy")
    @Expose
    private String madeBy;

    @SerializedName("modelNo")
    @Expose
    private String modelNo;
    @SerializedName("vehicleModelNo")
    @Expose
    private String vehicleModelNo;
    @SerializedName("engineNo")
    @Expose
    private String engineNo;
    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("encodedImage")
    @Expose
    private String encodedImage;

//    public GDViewModel(String gdFor, String gdDate,String gdNumber,Integer productTypeId,Integer gDTypeId,Integer gDInformationId,Integer vehicleId
//    ,Integer vehicleTypeId,Integer vehicleBrandId) {
//        this.name = name;
//        this.file = file;
//    }
//


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

    public Integer getGDInformationId() {
        return gDInformationId;
    }

    public void setGDInformationId(Integer gDInformationId) {
        this.gDInformationId = gDInformationId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public Integer getVehicleBrandId() {
        return vehicleBrandId;
    }

    public void setVehicleBrandId(Integer vehicleBrandId) {
        this.vehicleBrandId = vehicleBrandId;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public String getRegNoFirstPart() {
        return regNoFirstPart;
    }

    public void setRegNoFirstPart(String regNoFirstPart) {
        this.regNoFirstPart = regNoFirstPart;
    }

    public String getRegNoSecondPart() {
        return regNoSecondPart;
    }

    public void setRegNoSecondPart(String regNoSecondPart) {
        this.regNoSecondPart = regNoSecondPart;
    }

    public String getRegNoThiredPart() {
        return regNoThiredPart;
    }

    public void setRegNoThiredPart(String regNoThiredPart) {
        this.regNoThiredPart = regNoThiredPart;
    }

    public String getMadeBy() {
        return madeBy;
    }

    public void setMadeBy(String madeBy) {
        this.madeBy = madeBy;
    }


    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getVehicleModelNo() {
        return vehicleModelNo;
    }

    public void setVehicleModelNo(String vehicleModelNo) {
        this.vehicleModelNo = vehicleModelNo;
    }


    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

}
