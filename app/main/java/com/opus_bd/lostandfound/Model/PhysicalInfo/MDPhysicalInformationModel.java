
package com.opus_bd.lostandfound.Model.PhysicalInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MDPhysicalInformationModel {

    @SerializedName("beardTypes")
    @Expose
    private List<BeardType> beardTypes = null;
    @SerializedName("bodyChinTypes")
    @Expose
    private List<BodyChinType> bodyChinTypes = null;
    @SerializedName("bodyColors")
    @Expose
    private List<BodyColor> bodyColors = null;
    @SerializedName("bodyStructures")
    @Expose
    private List<BodyStructure> bodyStructures = null;
    @SerializedName("deadbodyConditions")
    @Expose
    private List<DeadbodyCondition> deadbodyConditions = null;
    @SerializedName("deathTypes")
    @Expose
    private List<DeathType> deathTypes = null;
    @SerializedName("earTypes")
    @Expose
    private List<EarType> earTypes = null;
    @SerializedName("eyeTypes")
    @Expose
    private List<EyeType> eyeTypes = null;
    @SerializedName("faceShapeTypes")
    @Expose
    private List<FaceShapeType> faceShapeTypes = null;
    @SerializedName("foreHeadTypes")
    @Expose
    private List<ForeHeadType> foreHeadTypes = null;
    @SerializedName("hairTypes")
    @Expose
    private List<HairType> hairTypes = null;
    @SerializedName("headTypes")
    @Expose
    private List<HeadType> headTypes = null;
    @SerializedName("moustacheTypes")
    @Expose
    private List<MoustacheType> moustacheTypes = null;
    @SerializedName("mouthTypes")
    @Expose
    private List<MouthType> mouthTypes = null;
    @SerializedName("neckTypes")
    @Expose
    private List<NeckType> neckTypes = null;
    @SerializedName("noseTypes")
    @Expose
    private List<NoseType> noseTypes = null;
    @SerializedName("specialBodyConditions")
    @Expose
    private List<SpecialBodyCondition> specialBodyConditions = null;
    @SerializedName("teethTypes")
    @Expose
    private List<TeethType> teethTypes = null;

    public List<BeardType> getBeardTypes() {
        return beardTypes;
    }

    public void setBeardTypes(List<BeardType> beardTypes) {
        this.beardTypes = beardTypes;
    }

    public List<BodyChinType> getBodyChinTypes() {
        return bodyChinTypes;
    }

    public void setBodyChinTypes(List<BodyChinType> bodyChinTypes) {
        this.bodyChinTypes = bodyChinTypes;
    }

    public List<BodyColor> getBodyColors() {
        return bodyColors;
    }

    public void setBodyColors(List<BodyColor> bodyColors) {
        this.bodyColors = bodyColors;
    }

    public List<BodyStructure> getBodyStructures() {
        return bodyStructures;
    }

    public void setBodyStructures(List<BodyStructure> bodyStructures) {
        this.bodyStructures = bodyStructures;
    }

    public List<DeadbodyCondition> getDeadbodyConditions() {
        return deadbodyConditions;
    }

    public void setDeadbodyConditions(List<DeadbodyCondition> deadbodyConditions) {
        this.deadbodyConditions = deadbodyConditions;
    }

    public List<DeathType> getDeathTypes() {
        return deathTypes;
    }

    public void setDeathTypes(List<DeathType> deathTypes) {
        this.deathTypes = deathTypes;
    }

    public List<EarType> getEarTypes() {
        return earTypes;
    }

    public void setEarTypes(List<EarType> earTypes) {
        this.earTypes = earTypes;
    }

    public List<EyeType> getEyeTypes() {
        return eyeTypes;
    }

    public void setEyeTypes(List<EyeType> eyeTypes) {
        this.eyeTypes = eyeTypes;
    }

    public List<FaceShapeType> getFaceShapeTypes() {
        return faceShapeTypes;
    }

    public void setFaceShapeTypes(List<FaceShapeType> faceShapeTypes) {
        this.faceShapeTypes = faceShapeTypes;
    }

    public List<ForeHeadType> getForeHeadTypes() {
        return foreHeadTypes;
    }

    public void setForeHeadTypes(List<ForeHeadType> foreHeadTypes) {
        this.foreHeadTypes = foreHeadTypes;
    }

    public List<HairType> getHairTypes() {
        return hairTypes;
    }

    public void setHairTypes(List<HairType> hairTypes) {
        this.hairTypes = hairTypes;
    }

    public List<HeadType> getHeadTypes() {
        return headTypes;
    }

    public void setHeadTypes(List<HeadType> headTypes) {
        this.headTypes = headTypes;
    }

    public List<MoustacheType> getMoustacheTypes() {
        return moustacheTypes;
    }

    public void setMoustacheTypes(List<MoustacheType> moustacheTypes) {
        this.moustacheTypes = moustacheTypes;
    }

    public List<MouthType> getMouthTypes() {
        return mouthTypes;
    }

    public void setMouthTypes(List<MouthType> mouthTypes) {
        this.mouthTypes = mouthTypes;
    }

    public List<NeckType> getNeckTypes() {
        return neckTypes;
    }

    public void setNeckTypes(List<NeckType> neckTypes) {
        this.neckTypes = neckTypes;
    }

    public List<NoseType> getNoseTypes() {
        return noseTypes;
    }

    public void setNoseTypes(List<NoseType> noseTypes) {
        this.noseTypes = noseTypes;
    }

    public List<SpecialBodyCondition> getSpecialBodyConditions() {
        return specialBodyConditions;
    }

    public void setSpecialBodyConditions(List<SpecialBodyCondition> specialBodyConditions) {
        this.specialBodyConditions = specialBodyConditions;
    }

    public List<TeethType> getTeethTypes() {
        return teethTypes;
    }

    public void setTeethTypes(List<TeethType> teethTypes) {
        this.teethTypes = teethTypes;
    }

}
