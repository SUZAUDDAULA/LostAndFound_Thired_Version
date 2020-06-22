
package com.opus_bd.lostandfound.Model.Dashboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusTypeViewModel {

    @SerializedName("complain")
    @Expose
    private Integer complain;
    @SerializedName("investigation")
    @Expose
    private Integer investigation;
    @SerializedName("finish")
    @Expose
    private Integer finish;
    @SerializedName("reject")
    @Expose
    private Integer reject;

    public Integer getComplain() {
        return complain;
    }

    public void setComplain(Integer complain) {
        this.complain = complain;
    }

    public Integer getInvestigation() {
        return investigation;
    }

    public void setInvestigation(Integer investigation) {
        this.investigation = investigation;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getReject() {
        return reject;
    }

    public void setReject(Integer reject) {
        this.reject = reject;
    }

}
