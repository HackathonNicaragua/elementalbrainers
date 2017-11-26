package com.elementalbraines.expressapp.api;

import com.elementalbraines.expressapp.models.FraseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Maurel on 26/11/2017.
 */

public interface WebServiceInterface {

    @POST("phrases/search/")
    @FormUrlEncoded
    Call<FraseModel>
    getFrases(@Field("frases") ArrayList<String> frases);


}

