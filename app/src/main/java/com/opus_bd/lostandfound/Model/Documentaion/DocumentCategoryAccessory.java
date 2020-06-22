
package com.opus_bd.lostandfound.Model.Documentaion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentCategoryAccessory {

    @SerializedName("documentTypeId")
    @Expose
    private Integer documentTypeId;
    @SerializedName("documentType")
    @Expose
    private Object documentType;
    @SerializedName("documentCategoryId")
    @Expose
    private Object documentCategoryId;
    @SerializedName("documentCategory")
    @Expose
    private Object documentCategory;
    @SerializedName("accessoriesName")
    @Expose
    private String accessoriesName;
    @SerializedName("accessoriesNameBn")
    @Expose
    private String accessoriesNameBn;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
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

    public Integer getDocumentTypeId() {
        return documentTypeId;
    }

    public void setDocumentTypeId(Integer documentTypeId) {
        this.documentTypeId = documentTypeId;
    }

    public Object getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Object documentType) {
        this.documentType = documentType;
    }

    public Object getDocumentCategoryId() {
        return documentCategoryId;
    }

    public void setDocumentCategoryId(Object documentCategoryId) {
        this.documentCategoryId = documentCategoryId;
    }

    public Object getDocumentCategory() {
        return documentCategory;
    }

    public void setDocumentCategory(Object documentCategory) {
        this.documentCategory = documentCategory;
    }

    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public String getAccessoriesNameBn() {
        return accessoriesNameBn;
    }

    public void setAccessoriesNameBn(String accessoriesNameBn) {
        this.accessoriesNameBn = accessoriesNameBn;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
