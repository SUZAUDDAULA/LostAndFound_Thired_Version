
package com.opus_bd.lostandfound.Model.Documentaion;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MDPersonalInformationModel {

    @SerializedName("religions")
    @Expose
    private List<Religion> religions = null;
    @SerializedName("religionCusts")
    @Expose
    private List<ReligionCust> religionCusts = null;
    @SerializedName("maritalStatuses")
    @Expose
    private List<MaritalStatus> maritalStatuses = null;
    @SerializedName("relationTypes")
    @Expose
    private List<RelationType> relationTypes = null;
    @SerializedName("occupations")
    @Expose
    private List<Occupation> occupations = null;
    @SerializedName("habits")
    @Expose
    private List<Habit> habits = null;
    @SerializedName("speeches")
    @Expose
    private List<Speech> speeches = null;

    public List<Religion> getReligions() {
        return religions;
    }

    public void setReligions(List<Religion> religions) {
        this.religions = religions;
    }

    public List<ReligionCust> getReligionCusts() {
        return religionCusts;
    }

    public void setReligionCusts(List<ReligionCust> religionCusts) {
        this.religionCusts = religionCusts;
    }

    public List<MaritalStatus> getMaritalStatuses() {
        return maritalStatuses;
    }

    public void setMaritalStatuses(List<MaritalStatus> maritalStatuses) {
        this.maritalStatuses = maritalStatuses;
    }

    public List<RelationType> getRelationTypes() {
        return relationTypes;
    }

    public void setRelationTypes(List<RelationType> relationTypes) {
        this.relationTypes = relationTypes;
    }

    public List<Occupation> getOccupations() {
        return occupations;
    }

    public void setOccupations(List<Occupation> occupations) {
        this.occupations = occupations;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public List<Speech> getSpeeches() {
        return speeches;
    }

    public void setSpeeches(List<Speech> speeches) {
        this.speeches = speeches;
    }

}
