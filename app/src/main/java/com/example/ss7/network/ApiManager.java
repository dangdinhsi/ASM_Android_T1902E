package com.example.ss7.network;

import com.example.ss7.model.Item;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiManager {
    String DOMAIN ="https://api-demo-anhth.herokuapp.com/";
    @GET("datas.json")
    Call<List<Item>> getListData();
}
