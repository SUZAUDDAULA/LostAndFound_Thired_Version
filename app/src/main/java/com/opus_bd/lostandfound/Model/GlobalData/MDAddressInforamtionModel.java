
package com.opus_bd.lostandfound.Model.GlobalData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.opus_bd.lostandfound.Model.Documentaion.NationalIdentityTypesModel;

import java.util.List;

public class MDAddressInforamtionModel {
    @SerializedName("nationalIdentityTypes")
    @Expose
    private List<NationalIdentityTypesModel> nationalIdentityTypes = null;
    @SerializedName("countries")
    @Expose
    private List<Country> countries = null;
    @SerializedName("divisions")
    @Expose
    private List<Division> divisions = null;
    @SerializedName("districts")
    @Expose
    private List<District> districts = null;
    @SerializedName("thanas")
    @Expose
    private List<Thana> thanas = null;

    public List<NationalIdentityTypesModel> getNationalIdentityTypesModels() {
        return nationalIdentityTypes;
    }

    public void setNationalIdentityTypesModels(List<NationalIdentityTypesModel> nationalIdentityTypes) {
        this.nationalIdentityTypes = nationalIdentityTypes;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Thana> getThanas() {
        return thanas;
    }

    public void setThanas(List<Thana> thanas) {
        this.thanas = thanas;
    }

}
