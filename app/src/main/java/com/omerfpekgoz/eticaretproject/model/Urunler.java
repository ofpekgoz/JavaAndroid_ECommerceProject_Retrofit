package com.omerfpekgoz.eticaretproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Urunler {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("urun_ad")
    @Expose
    private String urunAd;
    @SerializedName("urun_kategori")
    @Expose
    private String urunKategori;
    @SerializedName("urun_satisfiyat")
    @Expose
    private String urunSatisfiyat;
    @SerializedName("urun_stokdurumu")
    @Expose
    private String urunStokdurumu;
    @SerializedName("urun_esnafid")
    @Expose
    private String urunEsnafid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrunAd() {
        return urunAd;
    }

    public void setUrunAd(String urunAd) {
        this.urunAd = urunAd;
    }

    public String getUrunKategori() {
        return urunKategori;
    }

    public void setUrunKategori(String urunKategori) {
        this.urunKategori = urunKategori;
    }

    public String getUrunSatisfiyat() {
        return urunSatisfiyat;
    }

    public void setUrunSatisfiyat(String urunSatisfiyat) {
        this.urunSatisfiyat = urunSatisfiyat;
    }

    public String getUrunStokdurumu() {
        return urunStokdurumu;
    }

    public void setUrunStokdurumu(String urunStokdurumu) {
        this.urunStokdurumu = urunStokdurumu;
    }

    public String getUrunEsnafid() {
        return urunEsnafid;
    }

    public void setUrunEsnafid(String urunEsnafid) {
        this.urunEsnafid = urunEsnafid;
    }
}