
package com.opus_bd.lostandfound.Model.GDInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Headers {

    @SerializedName("Content-Disposition")
    @Expose
    private List<String> contentDisposition = null;
    @SerializedName("Content-Type")
    @Expose
    private List<String> contentType = null;

    public List<String> getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(List<String> contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public List<String> getContentType() {
        return contentType;
    }

    public void setContentType(List<String> contentType) {
        this.contentType = contentType;
    }

}
