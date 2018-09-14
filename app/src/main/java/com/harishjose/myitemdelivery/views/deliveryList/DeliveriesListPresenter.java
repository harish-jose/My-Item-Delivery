package com.harishjose.myitemdelivery.views.deliveryList;

import android.content.Context;

import com.harishjose.myitemdelivery.callbacks.DataCallback;
import com.harishjose.myitemdelivery.models.Item;
import com.harishjose.myitemdelivery.services.api.DeliveriesService;
import com.harishjose.myitemdelivery.services.api.DeliveriesServiceImpl;

import java.util.ArrayList;

/**
 * Created by harish.jose on 14-09-2018.
 */
public class DeliveriesListPresenter implements DeliveriesListContract.DeliveriesListPresenter {

    private DeliveriesListContract.DeliveriesListView view;
    private DeliveriesService service = new DeliveriesServiceImpl();

    @Override
    public void fetchDeliveriesList() {
        service.getDeliveries(new DataCallback<ArrayList<Item>, String>() {
            @Override
            public void onSuccess(ArrayList<Item> successResponse) {
                if(view != null) {
                    view.setDeliveriesList(successResponse);
                    view.hideShimmer(false);
                }
            }

            @Override
            public void onFailure(String errorResponse) {
                if(view != null) {
                    view.showToast(errorResponse);
                    view.hideShimmer(true);
                }
            }
        });
    }

    @Override
    public void setView(DeliveriesListContract.DeliveriesListView deliveriesListView) {
        this.view = deliveriesListView;
    }

    @Override
    public void onStop() {
        this.view = null;
    }
}
