
package com.opus_bd.lostandfound.Model.VehicleSearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GDInformation {

    @SerializedName("ApplicationUserId")
    @Expose
    private String applicationUserId;
    @SerializedName("ApplicationUser")
    @Expose
    private Object applicationUser;
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
    @SerializedName("productType")
    @Expose
    private Object productType;
    @SerializedName("gDTypeId")
    @Expose
    private Integer gDTypeId;
    @SerializedName("gDType")
    @Expose
    private Object gDType;
    @SerializedName("documentTypeId")
    @Expose
    private Object documentTypeId;
    @SerializedName("documentType")
    @Expose
    private Object documentType;
    @SerializedName("documentDescription")
    @Expose
    private String documentDescription;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("date")
    @Expose
    private Object date;
    @SerializedName("gdDescription")
    @Expose
    private String gdDescription;
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

    public String getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(String applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    public Object getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(Object applicationUser) {
        this.applicationUser = applicationUser;
    }

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

    public Object getProductType() {
        return productType;
    }

    public void setProductType(Object productType) {
        this.productType = productType;
    }

    public Integer getGDTypeId() {
        return gDTypeId;
    }

    public void setGDTypeId(Integer gDTypeId) {
        this.gDTypeId = gDTypeId;
    }

    public Object getGDType() {
        return gDType;
    }

    public void setGDType(Object gDType) {
        this.gDType = gDType;
    }

    public Object getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Object documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Object getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Object documentType) {
        this.documentType = documentType;
    }

    public String getDocumentDescription() {
        return documentDescription;
    }

    public void setDocumentDescription(String documentDescription) {
        this.documentDescription = documentDescription;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getDate() {
        return date;
    }

    public void setDate(Object date) {
        this.date = date;
    }

    public String getGdDescription() {
        return gdDescription;
    }

    public void setGdDescription(String gdDescription) {
        this.date = gdDescription;
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
