package com.omerfpekgoz.eticaretproject.service;

import com.omerfpekgoz.eticaretproject.model.CRUDResponse;
import com.omerfpekgoz.eticaretproject.model.UyelerResponse;

import java.sql.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IUyelerDao {


    @POST("eticaret/insert_uye.php")
    @FormUrlEncoded
    Call<CRUDResponse> uyeEkle(@Field("uye_eposta") String uye_eposta
            , @Field("uye_sifre") String uye_sifre
            , @Field("uye_telno") String uye_telno
            ,@Field("uye_dtarih") Date uye_dtarih
            ,@Field("uye_kayitturu") boolean uye_kayitturu);


    @POST("eticaret/giris_yap.php")
    @FormUrlEncoded
    Call<UyelerResponse> uyeGiris(@Field("uye_eposta") String uye_eposta
            , @Field("uye_sifre") String uye_sifre);




}
