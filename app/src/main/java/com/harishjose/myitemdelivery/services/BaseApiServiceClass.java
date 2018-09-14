package com.harishjose.myitemdelivery.services;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.harishjose.myitemdelivery.controller.RetrofitBase;
import com.harishjose.myitemdelivery.models.BaseModel;

import java.lang.annotation.Annotation;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;


/**
 * Created by harish.jose on 14-09-18.
 */

public abstract class BaseApiServiceClass {

    protected ServiceCalls getBaseApiInterface(Bundle bundle) {
        return RetrofitBase.getRetrofitInstance(bundle).create(ServiceCalls.class);
    }

    public BaseModel parseError(Context context, Bundle bundle, Response<?> response) {
        Converter<ResponseBody, BaseModel> converter =
                RetrofitBase.getRetrofitInstance(bundle)
                        .responseBodyConverter(BaseModel.class, new Annotation[0]);
        BaseModel baseModel;
        try {
            baseModel = converter.convert(response.errorBody());
        } catch (Exception e) {
            Log.e(context.getClass().toString(), e.getMessage(), e);
            baseModel = new BaseModel();
        }
        return baseModel;
    }
}
