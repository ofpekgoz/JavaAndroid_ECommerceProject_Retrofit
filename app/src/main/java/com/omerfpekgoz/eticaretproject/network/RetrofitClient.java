package com.omerfpekgoz.eticaretproject.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitClient {

    private static Retrofit retrofit=null;

    public static Retrofit getClient(String baseUrl){
        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
