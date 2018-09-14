package com.harishjose.myitemdelivery.services.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.harishjose.myitemdelivery.callbacks.DataCallback;
import com.harishjose.myitemdelivery.controller.MyItemDeliveryApplication;
import com.harishjose.myitemdelivery.models.BaseModel;
import com.harishjose.myitemdelivery.models.Item;
import com.harishjose.myitemdelivery.services.BaseApiServiceClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by harish.jose on 14-09-2018.
 */
public class DeliveriesServiceImpl extends BaseApiServiceClass implements DeliveriesService {

    @Override
    public void getDeliveries(final DataCallback<ArrayList<Item>, String> callback) {
        Call<BaseModel<ArrayList<Item>>> get = getBaseApiInterface(null).getDeliveries();
        get.enqueue(new Callback<BaseModel<ArrayList<Item>>>() {
            @Override
            public void onResponse(Call<BaseModel<ArrayList<Item>>> call, Response<BaseModel<ArrayList<Item>>> response) {
                if (response.body() != null) {
                    if (response.code() == HttpsURLConnection.HTTP_OK && response.body().isStatus() && response.body().getData() != null) {
                        callback.onSuccess(response.body().getData());
                        mCreateAndSaveFile("cachedData.json", response.body());
                    } else {
                        callback.onFailure(response.message());
                    }
                } else {
                    callback.onSuccess(mReadJsonData("cachedData.json"));
                }
            }

            @Override
            public void onFailure(Call<BaseModel<ArrayList<Item>>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });

    }

    public void mCreateAndSaveFile(String params, BaseModel<ArrayList<Item>> dataModel) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String mJsonResponse = gson.toJson(dataModel);
        try {
            FileWriter file = null;
            try {
                file = new FileWriter("/data/data/" + MyItemDeliveryApplication.getInstance().getPackageName() + "/" + params);
            } catch (IOException e) {
                e.printStackTrace();
            }
            file.write(mJsonResponse);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> mReadJsonData(String params) {
        try {
            File f = new File("/data/data/" + MyItemDeliveryApplication.getInstance().getPackageName() + "/" + params);
            FileInputStream is = new FileInputStream(f);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String mResponse = new String(buffer);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            BaseModel<ArrayList<Item>> dataModel ;
            dataModel = gson.fromJson(mResponse, new TypeToken<BaseModel<ArrayList<Item>>>(){}.getType());
            return dataModel.getData();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ArrayList<Item>();
    }
}
