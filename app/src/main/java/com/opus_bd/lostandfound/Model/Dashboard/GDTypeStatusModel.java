
package com.opus_bd.lostandfound.Model.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GDTypeStatusModel {

    @SerializedName("lostCnt")
    @Expose
    private Integer lostCnt;
    @SerializedName("theftCnt")
    @Expose
    private Integer theftCnt;
    @SerializedName("foundCnt")
    @Expose
    private Integer foundCnt;

    public Integer getLostCnt() {
        return lostCnt;
    }

    public void setLostCnt(Integer lostCnt) {
        this.lostCnt = lostCnt;
    }

    public Integer getTheftCnt() {
        return theftCnt;
    }

    public void setTheftCnt(Integer theftCnt) {
        this.theftCnt = theftCnt;
    }

    public Integer getFoundCnt() {
        return foundCnt;
    }

    public void setFoundCnt(Integer foundCnt) {
        this.foundCnt = foundCnt;
    }

}
