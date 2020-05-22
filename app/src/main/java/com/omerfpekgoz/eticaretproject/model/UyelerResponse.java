package com.omerfpekgoz.eticaretproject.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UyelerResponse {

    @SerializedName("uyeler")
    @Expose
    private List<Uyeler> uyeler = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Uyeler> getUyeler() {
        return uyeler;
    }

    public void setUyeler(List<Uyeler> uyeler) {
        this.uyeler = uyeler;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}