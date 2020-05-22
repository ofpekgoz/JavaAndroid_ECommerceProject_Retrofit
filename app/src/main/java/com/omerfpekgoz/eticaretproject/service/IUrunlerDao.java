package com.omerfpekgoz.eticaretproject.service;

import com.omerfpekgoz.eticaretproject.model.CRUDResponse;
import com.omerfpekgoz.eticaretproject.model.UrunlerResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IUrunlerDao {

    @GET("eticaret/tum_urunler.php")
    Call<UrunlerResponse> tumUrunler();

    @POST("eticaret/kategori_filtreli_urunler.php")
    @FormUrlEncoded
    Call<UrunlerResponse> tumUrunlerByEsnafId(@Field("urun_esnafid") int urun_esnafid
            , @Field("urun_kategori") String urun_kategori);


    @POST("eticaret/kategori_adina_gore_urun_adlari.php")
    @FormUrlEncoded
    Call<UrunlerResponse> urunAdlariByKategoriAd(@Field("urun_esnafid") int urun_esnafid
            , @Field("urun_kategori") String urun_kategori);

    @POST("eticaret/esnaf_id_gore_kategori_adlari.php")
    @FormUrlEncoded
    Call<UrunlerResponse> kategoriAdByEsnafId(@Field("urun_esnafid") int urun_esnafid);

    @POST("eticaret/urun_adi_gore_bilgiler.php")
    @FormUrlEncoded
    Call<UrunlerResponse> bilgilerByUrunAdi(@Field("urun_esnafid") int urun_esnafid
            , @Field("urun_kategori") String urun_kategori
            , @Field("urun_ad") String urun_ad);


    @POST("eticaret/insert_urun.php")
    @FormUrlEncoded
    Call<CRUDResponse> urunEkle(@Field("urun_ad") String urun_ad
            , @Field("urun_kategori") String urun_kategori
            , @Field("urun_satisfiyat") float urun_satisfiyat
            , @Field("urun_stokdurumu") int urun_stokdurumu
            , @Field("urun_esnafid") int urun_esnafid);

    @POST("eticaret/update_urun.php")
    @FormUrlEncoded
    Call<CRUDResponse> urunGuncelle(@Field("id") int id
            , @Field("urun_satisfiyat") float urun_satisfiyat
            , @Field("urun_stokdurumu") int urun_stokdurumu);

    @POST("eticaret/delete_urun.php")
    @FormUrlEncoded
    Call<CRUDResponse> urunSil(@Field("id") int id
            , @Field("urun_esnafid") int urun_esnafid);

}
