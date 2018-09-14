package com.harishjose.myitemdelivery.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harish.jose on 14-09-2018.
 */
public class BaseModel<T> {
    @SerializedName("status")
    private boolean status;
    @SerializedName("data")
    private T data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
