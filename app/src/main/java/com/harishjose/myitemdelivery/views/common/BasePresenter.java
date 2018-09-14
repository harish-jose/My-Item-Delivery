package com.harishjose.myitemdelivery.views.common;

import android.content.Context;

/**
 * Created by harish.jose on 02-09-2018.
 */

public interface BasePresenter<T> {
    void setView(T t);
    void onStop();
}
