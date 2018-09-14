package com.harishjose.myitemdelivery.services;

import com.harishjose.myitemdelivery.models.BaseModel;
import com.harishjose.myitemdelivery.models.Item;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by harish.jose on 14-09-2018.
 */
public interface ServiceCalls {

    @GET("5b9ba4e43000008700f6b3aa/deliveries")
    Call<BaseModel<ArrayList<Item>>> getDeliveries();

}
