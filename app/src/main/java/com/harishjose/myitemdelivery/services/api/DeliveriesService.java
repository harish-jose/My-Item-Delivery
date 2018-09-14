package com.harishjose.myitemdelivery.services.api;

import com.harishjose.myitemdelivery.callbacks.DataCallback;
import com.harishjose.myitemdelivery.models.Item;

import java.util.ArrayList;

/**
 * Created by harish.jose on 14-09-2018.
 */
public interface DeliveriesService {
    void getDeliveries(DataCallback<ArrayList<Item>, String> callback);
}
