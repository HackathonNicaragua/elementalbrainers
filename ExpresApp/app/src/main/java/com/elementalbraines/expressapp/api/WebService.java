package com.elementalbraines.expressapp.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maurel on 26/11/2017.
 */

public class WebService {

    private final static String URL = "http://172.20.9.45:3000/api/";


    public static WebServiceInterface instance() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .client(getRequestHeader())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WebServiceInterface.class);
    }


    private static OkHttpClient getRequestHeader() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(6000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(6000, TimeUnit.MILLISECONDS);

        return builder.build();

    }

}
