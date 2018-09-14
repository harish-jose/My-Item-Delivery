package com.harishjose.myitemdelivery.views.deliveryList;

import com.harishjose.myitemdelivery.models.Item;
import com.harishjose.myitemdelivery.views.common.BasePresenter;

import java.util.ArrayList;

/**
 * Created by harish.jose on 14-09-2018.
 */
class DeliveriesListContract {
    public interface DeliveriesListView{
        void setDeliveriesList(ArrayList<Item> dataList);
        void showToast(String message);
        void hideShimmer(boolean isVisible);
    }

    public interface DeliveriesListPresenter extends BasePresenter<DeliveriesListView> {
        void fetchDeliveriesList();
    }
}
