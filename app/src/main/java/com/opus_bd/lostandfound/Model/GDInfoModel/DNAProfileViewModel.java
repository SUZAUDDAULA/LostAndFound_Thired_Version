
package com.opus_bd.lostandfound.Model.GDInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DNAProfileViewModel {

    @SerializedName("locous")
    @Expose
    private String locous;
    @SerializedName("genotype1")
    @Expose
    private String genotype1;
    @SerializedName("genotype2")
    @Expose
    private String genotype2;

    public String getLocous() {
        return locous;
    }

    public void setLocous(String locous) {
        this.locous = locous;
    }

    public String getGenotype1() {
        return genotype1;
    }

    public void setGenotype1(String genotype1) {
        this.genotype1 = genotype1;
    }

    public String getGenotype2() {
        return genotype2;
    }

    public void setGenotype2(String genotype2) {
        this.genotype2 = genotype2;
    }

}
