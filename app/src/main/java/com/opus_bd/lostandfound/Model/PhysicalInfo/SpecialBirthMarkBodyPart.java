
package com.opus_bd.lostandfound.Model.PhysicalInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialBirthMarkBodyPart {

    @SerializedName("bodyName")
    @Expose
    private String bodyName;
    @SerializedName("bodyNameBn")
    @Expose
    private String bodyNameBn;
    @SerializedName("shortOrder")
    @Expose
    private Integer shortOrder;
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

    public String getBodyName() {
        return bodyName;
    }

    public void setBodyName(String bodyName) {
        this.bodyName = bodyName;
    }

    public String getBodyNameBn() {
        return bodyNameBn;
    }

    public void setBodyNameBn(String bodyNameBn) {
        this.bodyNameBn = bodyNameBn;
    }

    public Integer getShortOrder() {
        return shortOrder;
    }

    public void setShortOrder(Integer shortOrder) {
        this.shortOrder = shortOrder;
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
