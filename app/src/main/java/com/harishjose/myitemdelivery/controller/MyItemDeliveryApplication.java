package com.harishjose.myitemdelivery.controller;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by harish.jose on 14-09-2018.
 */
public class MyItemDeliveryApplication extends Application {
    private static MyItemDeliveryApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Fresco.initialize(this);
    }

    public static synchronized MyItemDeliveryApplication getInstance() {
        if (sInstance == null) {
            sInstance = new MyItemDeliveryApplication();
        }
        return sInstance;
    }


}
