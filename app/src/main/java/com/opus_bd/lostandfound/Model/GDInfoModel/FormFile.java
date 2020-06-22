
package com.opus_bd.lostandfound.Model.GDInfoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormFile {

    @SerializedName("ContentDisposition")
    @Expose
    private String contentDisposition;
    @SerializedName("ContentType")
    @Expose
    private String contentType;
    @SerializedName("Headers")
    @Expose
    private Headers headers;
    @SerializedName("Length")
    @Expose
    private Integer length;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("FileName")
    @Expose
    private String fileName;

    public String getContentDisposition() {
        return contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
