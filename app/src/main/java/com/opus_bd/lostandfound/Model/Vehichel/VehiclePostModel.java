
package com.opus_bd.lostandfound.Model.Vehichel;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.opus_bd.lostandfound.Model.GDInfoModel.FormFile;

public class VehiclePostModel {
    @SerializedName("gdTypeId")
    @Expose
    private Integer gdTypeId;
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
    @SerializedName("modelNo")
    @Expose
    private String modelNo;
    @SerializedName("engineNo")
    @Expose
    private String engineNo;
    @SerializedName("vehicleDescription")
    @Expose
    private String vehicleDescription;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("encodedImage")
    @Expose
    private String encodedImage;

    public VehiclePostModel(String encodedImage, Integer gdTypeId, Integer vehicleTypeId, Integer vehicleBrandId, String vehicleRegNo, String regNoFirstPart
        , String regNoSecondPart, String regNoThiredPart, String modelNo, String engineNo, String vehicleDescription, String userName) {
        this.encodedImage = encodedImage;
        this.vehicleTypeId=vehicleTypeId;
        this.vehicleBrandId=vehicleBrandId;
        this.vehicleRegNo=vehicleRegNo;
        this.regNoFirstPart=regNoFirstPart;
        this.regNoSecondPart=regNoSecondPart;
        this.regNoThiredPart=regNoThiredPart;
        this.modelNo=modelNo;
        this.engineNo=engineNo;
        this.vehicleDescription=vehicleDescription;
        this.userName=userName;
    }

    public Integer getGdTypeId() {
        return gdTypeId;
    }

    public void setGdTypeId(Integer gdTypeId) {
        this.gdTypeId = gdTypeId;
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

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
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

    @Override
    public String toString() {
        return "VehiclePostModel{" +

                ", vehicleTypeId=" + vehicleTypeId +
                ", vehicleBrandId=" + vehicleBrandId +
                ", vehicleRegNo='" + vehicleRegNo + '\'' +
                ", regNoFirstPart='" + regNoFirstPart + '\'' +
                ", regNoSecondPart='" + regNoSecondPart + '\'' +
                ", regNoThiredPart='" + regNoThiredPart + '\'' +
                ", madeBy='" + vehicleDescription + '\'' +

                ", modelNo='" + modelNo + '\'' +

                ", engineNo='" + engineNo + '\'' +

                ", userName='" + userName + '\'' +

                '}';
    }
}
