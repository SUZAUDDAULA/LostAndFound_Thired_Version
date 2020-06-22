
package com.opus_bd.lostandfound.Model.OtherBrand;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MDOtherItemInformation {

    @SerializedName("electronicsTypes")
    @Expose
    private List<ElectronicsType> electronicsTypes = null;
    @SerializedName("fileDocumentTypes")
    @Expose
    private List<FileDocumentType> fileDocumentTypes = null;
    @SerializedName("mobilePhoneTypes")
    @Expose
    private List<MobilePhoneType> mobilePhoneTypes = null;
    @SerializedName("mobileBrands")
    @Expose
    private List<MobileBrand> mobileBrands = null;
    @SerializedName("watchBrands")
    @Expose
    private List<WatchBrand> watchBrands = null;
    @SerializedName("shoesBrands")
    @Expose
    private List<ShoesBrand> shoesBrands = null;
    @SerializedName("bagBrands")
    @Expose
    private List<BagBrand> bagBrands = null;
    @SerializedName("electronicsBrands")
    @Expose
    private List<ElectronicsBrand> electronicsBrands = null;
    @SerializedName("jwellaryBrands")
    @Expose
    private List<JwellaryBrand> jwellaryBrands = null;
    @SerializedName("glassBrands")
    @Expose
    private List<GlassBrand> glassBrands = null;
    @SerializedName("umbrellaBrands")
    @Expose
    private List<UmbrellaBrand> umbrellaBrands = null;

    @SerializedName("operatingSystemTypes")
    @Expose
    private List<OperatingSystemType> operatingSystemTypes = null;

    public List<ElectronicsType> getElectronicsTypes() {
        return electronicsTypes;
    }

    public void setElectronicsTypes(List<ElectronicsType> electronicsTypes) {
        this.electronicsTypes = electronicsTypes;
    }

    public List<FileDocumentType> getFileDocumentTypes() {
        return fileDocumentTypes;
    }

    public void setFileDocumentTypes(List<FileDocumentType> fileDocumentTypes) {
        this.fileDocumentTypes = fileDocumentTypes;
    }

    public List<MobilePhoneType> getMobilePhoneTypes() {
        return mobilePhoneTypes;
    }

    public void setMobilePhoneTypes(List<MobilePhoneType> mobilePhoneTypes) {
        this.mobilePhoneTypes = mobilePhoneTypes;
    }

    public List<MobileBrand> getMobileBrands() {
        return mobileBrands;
    }

    public void setMobileBrands(List<MobileBrand> mobileBrands) {
        this.mobileBrands = mobileBrands;
    }

    public List<WatchBrand> getWatchBrands() {
        return watchBrands;
    }

    public void setWatchBrands(List<WatchBrand> watchBrands) {
        this.watchBrands = watchBrands;
    }

    public List<ShoesBrand> getShoesBrands() {
        return shoesBrands;
    }

    public void setShoesBrands(List<ShoesBrand> shoesBrands) {
        this.shoesBrands = shoesBrands;
    }

    public List<BagBrand> getBagBrands() {
        return bagBrands;
    }

    public void setBagBrands(List<BagBrand> bagBrands) {
        this.bagBrands = bagBrands;
    }

    public List<ElectronicsBrand> getElectronicsBrands() {
        return electronicsBrands;
    }

    public void setElectronicsBrands(List<ElectronicsBrand> electronicsBrands) {
        this.electronicsBrands = electronicsBrands;
    }

    public List<JwellaryBrand> getJwellaryBrands() {
        return jwellaryBrands;
    }

    public void setJwellaryBrands(List<JwellaryBrand> jwellaryBrands) {
        this.jwellaryBrands = jwellaryBrands;
    }

    public List<GlassBrand> getGlassBrands() {
        return glassBrands;
    }

    public void setGlassBrands(List<GlassBrand> glassBrands) {
        this.glassBrands = glassBrands;
    }

    public List<UmbrellaBrand> getUmbrellaBrands() {
        return umbrellaBrands;
    }

    public void setUmbrellaBrands(List<UmbrellaBrand> umbrellaBrands) {
        this.umbrellaBrands = umbrellaBrands;
    }

    public List<OperatingSystemType> getOperatingSystemTypes() {
        return operatingSystemTypes;
    }

    public void setOperatingSystemTypes(List<OperatingSystemType> operatingSystemTypes) {
        this.operatingSystemTypes = operatingSystemTypes;
    }

}
