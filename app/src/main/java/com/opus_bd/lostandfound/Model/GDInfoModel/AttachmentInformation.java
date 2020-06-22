package com.opus_bd.lostandfound.Model.GDInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AttachmentInformation {

    @SerializedName("gDInformationId")
    @Expose
    private Integer gDInformationId;

    @SerializedName("gDInformation")
    @Expose
    private Object gDInformation;

    @SerializedName("attachmentTypeId")
    @Expose
    private Integer attachmentTypeId;

    @SerializedName("masterId")
    @Expose
    private Integer masterId;

    @SerializedName("fileSubject")
    @Expose
    private String fileSubject;

    @SerializedName("fileName")
    @Expose
    private String fileName;

    @SerializedName("fileType")
    @Expose
    private String fileType;

    @SerializedName("filePath")
    @Expose
    private String filePath;

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
    private String updatedAt;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("updatedBy")
    @Expose
    private String updatedBy;

    public Integer getgDInformationId() {
        return gDInformationId;
    }

    public void setgDInformationId(Integer gDInformationId) {
        this.gDInformationId = gDInformationId;
    }

    public Object getgDInformation() {
        return gDInformation;
    }

    public void setgDInformation(Object gDInformation) {
        this.gDInformation = gDInformation;
    }

    public Integer getAttachmentTypeId() {
        return attachmentTypeId;
    }

    public void setAttachmentTypeId(Integer attachmentTypeId) {
        this.attachmentTypeId = attachmentTypeId;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getFileSubject() {
        return fileSubject;
    }

    public void setFileSubject(String fileSubject) {
        this.fileSubject = fileSubject;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
