package com.harishjose.myitemdelivery.controller;

import android.os.Bundle;


import com.harishjose.myitemdelivery.BuildConfig;
import com.harishjose.myitemdelivery.constants.ApiConstants;
import com.harishjose.myitemdelivery.constants.AppConstants;
import com.harishjose.myitemdelivery.utils.GeneralUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by harish.jose on 14-09-2018.
 */
public class RetrofitBase {
    private static Retrofit retrofit;
    private static final String APPLICATION_JSON = "application/json";
    private static final String NO_NETWORK_ERROR_BODY = "{\n" +
            "  \"status\": false,\n" +
            "  \"message\": \"Please check your internet connection and retry\", \n" +
            " \"code\": " + AppConstants.NO_INTERNET + ",\n" +
            " \"data\":" + null +
            "\n}";


    public static Retrofit getRetrofitInstance(final Bundle bundle) {
        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            if (BuildConfig.DEBUG) {
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            } else {
                logging.setLevel(HttpLoggingInterceptor.Level.NONE);
            }

            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
            httpClientBuilder.writeTimeout(60, TimeUnit.SECONDS);
            httpClientBuilder.readTimeout(60, TimeUnit.SECONDS);
            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    if (GeneralUtil.checkNetworkAvailability()) {
                        return chain.proceed(chain.request());
                    } else {
                        return new Response.Builder().body(ResponseBody.create(
                                MediaType.parse(APPLICATION_JSON),
                                NO_NETWORK_ERROR_BODY
                        )).protocol(Protocol.HTTP_1_1).code(AppConstants.NO_INTERNET)
                                .message("ERROR").
                                        request(chain.request()).build();
                    }
                }
            });
            httpClientBuilder.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClientBuilder.build())
                    .build();
        }
        return retrofit;
    }


}
