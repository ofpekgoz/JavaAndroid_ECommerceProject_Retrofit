package com.omerfpekgoz.eticaretproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Uyeler {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("uye_eposta")
    @Expose
    private String uyeEposta;
    @SerializedName("uye_sifre")
    @Expose
    private String uyeSifre;
    @SerializedName("uye_telno")
    @Expose
    private String uyeTelno;
    @SerializedName("uye_dtarih")
    @Expose
    private String uyeDtarih;
    @SerializedName("uye_kayitturu")
    @Expose
    private String uyeKayitturu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUyeEposta() {
        return uyeEposta;
    }

    public void setUyeEposta(String uyeEposta) {
        this.uyeEposta = uyeEposta;
    }

    public String getUyeSifre() {
        return uyeSifre;
    }

    public void setUyeSifre(String uyeSifre) {
        this.uyeSifre = uyeSifre;
    }

    public String getUyeTelno() {
        return uyeTelno;
    }

    public void setUyeTelno(String uyeTelno) {
        this.uyeTelno = uyeTelno;
    }

    public String getUyeDtarih() {
        return uyeDtarih;
    }

    public void setUyeDtarih(String uyeDtarih) {
        this.uyeDtarih = uyeDtarih;
    }

    public String getUyeKayitturu() {
        return uyeKayitturu;
    }

    public void setUyeKayitturu(String uyeKayitturu) {
        this.uyeKayitturu = uyeKayitturu;
    }

}