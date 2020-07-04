
package com.opus_bd.lostandfound.Model.GDInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsFeedViewModel {

    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("profilePic")
    @Expose
    private String profilePic;
    @SerializedName("gdNumber")
    @Expose
    private String gdNumber;
    @SerializedName("vehicleTypeName")
    @Expose
    private String vehicleTypeName;
    @SerializedName("gdDate")
    @Expose
    private String gdDate;
    @SerializedName("vehicleDescription")
    @Expose
    private String vehicleDescription;
    @SerializedName("attachImage")
    @Expose
    private String attachImage;

    @SerializedName("encodedImage")
    @Expose
    private String encodedImage;
    @SerializedName("vehicleId")
    @Expose
    private Integer vehicleId;
    @SerializedName("attachmentId")
    @Expose
    private Integer attachmentId;
    @SerializedName("totalLikes")
    @Expose
    private Integer totalLikes;
    @SerializedName("totalComments")
    @Expose
    private Integer totalComments;

    @SerializedName("statusId")
    @Expose
    private Integer statusId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getGdNumber() {
        return gdNumber;
    }

    public void setGdNumber(String gdNumber) {
        this.gdNumber = gdNumber;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getGdDate() {
        return gdDate;
    }

    public void setGdDate(String gdDate) {
        this.gdDate = gdDate;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public String getAttachImage() {
        return attachImage;
    }

    public void setAttachImage(String attachImage) {
        this.attachImage = attachImage;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
    public Integer getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Integer attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }
    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public Integer getStatusId() {
        return statusId;
    }
    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

}
