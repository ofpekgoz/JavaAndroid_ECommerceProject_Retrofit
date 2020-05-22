package com.omerfpekgoz.eticaretproject.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UrunlerResponse {

    @SerializedName("urunler")
    @Expose
    private List<Urunler> urunler = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Urunler> getUrunler() {
        return urunler;
    }

    public void setUrunler(List<Urunler> urunler) {
        this.urunler = urunler;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}

