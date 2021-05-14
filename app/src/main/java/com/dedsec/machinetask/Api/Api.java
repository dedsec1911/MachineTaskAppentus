package com.dedsec.machinetask.Api;

import com.dedsec.machinetask.Model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://picsum.photos/v2/";

    @GET("list?page=34")
    Call<List<Data>> getData();
}