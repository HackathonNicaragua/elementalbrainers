package com.elementalbraines.expressapp.api;

import com.elementalbraines.expressapp.models.FraseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

/**
 * Created by Maurel on 26/11/2017.
 */

public class Frases {

    public static void getFrases(ArrayList<String> frases, Callback<List<FraseModel>> callback){
        WebServiceInterface webService = WebService.instance();
        webService.getFrases(frases).enqueue(callback);
    }
}
