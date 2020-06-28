
package com.opus_bd.lostandfound.Model.Vehichel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {

    @SerializedName("applicationUserId")
    @Expose
    private String applicationUserId;
    @SerializedName("vehicleId")
    @Expose
    private Integer vehicleId;
    @SerializedName("attachmentId")
    @Expose
    private Integer attachmentId;
    @SerializedName("statusId")
    @Expose
    private Integer statusId;
    @SerializedName("total")
    @Expose
    private Integer total;

    public String getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(String applicationUserId) {
        this.applicationUserId = applicationUserId;
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
