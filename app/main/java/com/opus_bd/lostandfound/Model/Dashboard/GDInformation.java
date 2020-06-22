
package com.opus_bd.lostandfound.Model.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.opus_bd.lostandfound.Model.Documentaion.GDType;
import com.opus_bd.lostandfound.Model.Documentaion.ProductType;

public class GDInformation {

    @SerializedName("ApplicationUserId")
    @Expose
    private String applicationUserId;
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
    private ProductType productType;
    @SerializedName("gDTypeId")
    @Expose
    private Integer gDTypeId;
    @SerializedName("gDType")
    @Expose
    private GDType gDType;
    @SerializedName("documentTypeId")
    @Expose
    private Integer documentTypeId;
    @SerializedName("documentDescription")
    @Expose
    private String documentDescription;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("isDelete")
    @Expose
    private Integer isDelete;
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

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Integer getGDTypeId() {
        return gDTypeId;
    }

    public void setGDTypeId(Integer gDTypeId) {
        this.gDTypeId = gDTypeId;
    }

    public GDType getGDType() {
        return gDType;
    }

    public void setGDType(GDType gDType) {
        this.gDType = gDType;
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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
