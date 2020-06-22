
package com.opus_bd.lostandfound.Model.VehicleSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleSearchListModel {

    @SerializedName("gDInformationId")
    @Expose
    private Integer gDInformationId;
    @SerializedName("gDInformation")
    @Expose
    private GDInformation gDInformation;
    @SerializedName("vehicleTypeId")
    @Expose
    private Object vehicleTypeId;
    @SerializedName("vehicleType")
    @Expose
    private VehicleType vehicleType;
    @SerializedName("vehicleBrandId")
    @Expose
    private Object vehicleBrandId;
    @SerializedName("vehicleBrand")
    @Expose
    private VehicleBrand vehicleBrand;
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
    @SerializedName("madeIn")
    @Expose
    private String madeIn;
    @SerializedName("modelNo")
    @Expose
    private Object modelNo;
    @SerializedName("mfcDate")
    @Expose
    private String mfcDate;
    @SerializedName("engineNo")
    @Expose
    private String engineNo;
    @SerializedName("chasisNo")
    @Expose
    private String chasisNo;
    @SerializedName("ccNo")
    @Expose
    private String ccNo;
    @SerializedName("vehicleModelNo")
    @Expose
    private Object vehicleModelNo;
    @SerializedName("indentifyInfo")
    @Expose
    private IndentifyInfo indentifyInfo;
    @SerializedName("spaceAndTime")
    @Expose
    private SpaceAndTime spaceAndTime;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("isDelete")
    @Expose
    private Object isDelete;
    @SerializedName("createdAt")
    @Expose
    private Object createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;

    public Integer getGDInformationId() {
        return gDInformationId;
    }

    public void setGDInformationId(Integer gDInformationId) {
        this.gDInformationId = gDInformationId;
    }

    public GDInformation getGDInformation() {
        return gDInformation;
    }

    public void setGDInformation(GDInformation gDInformation) {
        this.gDInformation = gDInformation;
    }

    public Object getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Object vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Object getVehicleBrandId() {
        return vehicleBrandId;
    }

    public void setVehicleBrandId(Object vehicleBrandId) {
        this.vehicleBrandId = vehicleBrandId;
    }

    public VehicleBrand getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(VehicleBrand vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
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

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public Object getModelNo() {
        return modelNo;
    }

    public void setModelNo(Object modelNo) {
        this.modelNo = modelNo;
    }

    public String getMfcDate() {
        return mfcDate;
    }

    public void setMfcDate(String mfcDate) {
        this.mfcDate = mfcDate;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getChasisNo() {
        return chasisNo;
    }

    public void setChasisNo(String chasisNo) {
        this.chasisNo = chasisNo;
    }

    public String getCcNo() {
        return ccNo;
    }

    public void setCcNo(String ccNo) {
        this.ccNo = ccNo;
    }

    public Object getVehicleModelNo() {
        return vehicleModelNo;
    }

    public void setVehicleModelNo(Object vehicleModelNo) {
        this.vehicleModelNo = vehicleModelNo;
    }

    public IndentifyInfo getIndentifyInfo() {
        return indentifyInfo;
    }

    public void setIndentifyInfo(IndentifyInfo indentifyInfo) {
        this.indentifyInfo = indentifyInfo;
    }

    public SpaceAndTime getSpaceAndTime() {
        return spaceAndTime;
    }

    public void setSpaceAndTime(SpaceAndTime spaceAndTime) {
        this.spaceAndTime = spaceAndTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Object isDelete) {
        this.isDelete = isDelete;
    }

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

}
