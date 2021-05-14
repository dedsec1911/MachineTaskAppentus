package com.dedsec.machinetask.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dedsec.machinetask.Api.Api;
import com.dedsec.machinetask.Model.Data;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataViewModel extends ViewModel {

    private MutableLiveData<List<Data>> dataList;

    public LiveData<List<Data>> getData() {
        if (dataList == null) {
            dataList = new MutableLiveData<>();
            loadData();
        }
        return dataList;
    }

    private void loadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<Data>> call = api.getData();


        call.enqueue(new Callback<List<Data>>() {
            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                dataList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.e("ERROR: ", Arrays.toString(t.getStackTrace()));
            }
        });
    }
}