
package com.opus_bd.lostandfound.Model.GlobalData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thana {

    @SerializedName("thanaCode")
    @Expose
    private String thanaCode;
    @SerializedName("thanaName")
    @Expose
    private String thanaName;
    @SerializedName("shortName")
    @Expose
    private Integer shortName;
    @SerializedName("thanaNameBn")
    @Expose
    private String thanaNameBn;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("isDelete")
    @Expose
    private Integer isDelete;
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

    public String getThanaCode() {
        return thanaCode;
    }

    public void setThanaCode(String thanaCode) {
        this.thanaCode = thanaCode;
    }

    public String getThanaName() {
        return thanaName;
    }

    public void setThanaName(String thanaName) {
        this.thanaName = thanaName;
    }

    public Integer getShortName() {
        return shortName;
    }

    public void setShortName(Integer shortName) {
        this.shortName = shortName;
    }

    public String getThanaNameBn() {
        return thanaNameBn;
    }

    public void setThanaNameBn(String thanaNameBn) {
        this.thanaNameBn = thanaNameBn;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
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
