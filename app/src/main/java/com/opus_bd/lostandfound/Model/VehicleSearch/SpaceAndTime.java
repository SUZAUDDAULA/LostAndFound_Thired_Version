
package com.opus_bd.lostandfound.Model.VehicleSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpaceAndTime {

    @SerializedName("gDInformationId")
    @Expose
    private Integer gDInformationId;
    @SerializedName("gDInformation")
    @Expose
    private GDInformation gDInformation;
    @SerializedName("divisionId")
    @Expose
    private Object divisionId;
    @SerializedName("division")
    @Expose
    private Object division;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("district")
    @Expose
    private Object district;
    @SerializedName("thanaId")
    @Expose
    private Integer thanaId;
    @SerializedName("thana")
    @Expose
    private Object thana;
    @SerializedName("postOfficeId")
    @Expose
    private Object postOfficeId;
    @SerializedName("postOffice")
    @Expose
    private Object postOffice;
    @SerializedName("village")
    @Expose
    private Object village;
    @SerializedName("placeDetails")
    @Expose
    private String placeDetails;
    @SerializedName("lafDate")
    @Expose
    private String lafDate;
    @SerializedName("lafTime")
    @Expose
    private String lafTime;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("isDelete")
    @Expose
    private Object isDelete;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
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

    public Object getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(Object divisionId) {
        this.divisionId = divisionId;
    }

    public Object getDivision() {
        return division;
    }

    public void setDivision(Object division) {
        this.division = division;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Object getDistrict() {
        return district;
    }

    public void setDistrict(Object district) {
        this.district = district;
    }

    public Integer getThanaId() {
        return thanaId;
    }

    public void setThanaId(Integer thanaId) {
        this.thanaId = thanaId;
    }

    public Object getThana() {
        return thana;
    }

    public void setThana(Object thana) {
        this.thana = thana;
    }

    public Object getPostOfficeId() {
        return postOfficeId;
    }

    public void setPostOfficeId(Object postOfficeId) {
        this.postOfficeId = postOfficeId;
    }

    public Object getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(Object postOffice) {
        this.postOffice = postOffice;
    }

    public Object getVillage() {
        return village;
    }

    public void setVillage(Object village) {
        this.village = village;
    }

    public String getPlaceDetails() {
        return placeDetails;
    }

    public void setPlaceDetails(String placeDetails) {
        this.placeDetails = placeDetails;
    }

    public String getLafDate() {
        return lafDate;
    }

    public void setLafDate(String lafDate) {
        this.lafDate = lafDate;
    }

    public String getLafTime() {
        return lafTime;
    }

    public void setLafTime(String lafTime) {
        this.lafTime = lafTime;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

}
