
package com.opus_bd.lostandfound.Model.DressInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MDDressInformationModel {

    @SerializedName("inTheBodies")
    @Expose
    private List<InTheBody> inTheBodies = null;
    @SerializedName("inTheHeads")
    @Expose
    private List<InTheHead> inTheHeads = null;
    @SerializedName("inTheEyes")
    @Expose
    private List<InTheEye> inTheEyes = null;
    @SerializedName("inTheLegs")
    @Expose
    private List<InTheLeg> inTheLegs = null;
    @SerializedName("inTheThroats")
    @Expose
    private List<InTheThroat> inTheThroats = null;
    @SerializedName("inTheWaists")
    @Expose
    private List<InTheWaist> inTheWaists = null;

    public List<InTheBody> getInTheBodies() {
        return inTheBodies;
    }

    public void setInTheBodies(List<InTheBody> inTheBodies) {
        this.inTheBodies = inTheBodies;
    }

    public List<InTheHead> getInTheHeads() {
        return inTheHeads;
    }

    public void setInTheHeads(List<InTheHead> inTheHeads) {
        this.inTheHeads = inTheHeads;
    }

    public List<InTheEye> getInTheEyes() {
        return inTheEyes;
    }

    public void setInTheEyes(List<InTheEye> inTheEyes) {
        this.inTheEyes = inTheEyes;
    }

    public List<InTheLeg> getInTheLegs() {
        return inTheLegs;
    }

    public void setInTheLegs(List<InTheLeg> inTheLegs) {
        this.inTheLegs = inTheLegs;
    }

    public List<InTheThroat> getInTheThroats() {
        return inTheThroats;
    }

    public void setInTheThroats(List<InTheThroat> inTheThroats) {
        this.inTheThroats = inTheThroats;
    }

    public List<InTheWaist> getInTheWaists() {
        return inTheWaists;
    }

    public void setInTheWaists(List<InTheWaist> inTheWaists) {
        this.inTheWaists = inTheWaists;
    }

}
