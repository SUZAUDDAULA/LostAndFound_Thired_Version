package com.opus_bd.lostandfound.Model.GlobalData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FileUpload {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("file")
    @Expose
    private String file;

    public FileUpload(String name, String file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setBrandName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
