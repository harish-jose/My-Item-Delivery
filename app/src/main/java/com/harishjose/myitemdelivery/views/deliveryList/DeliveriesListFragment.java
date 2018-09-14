package com.harishjose.myitemdelivery.views.deliveryList;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harishjose.myitemdelivery.callbacks.OnClickPositionCallback;
import com.harishjose.myitemdelivery.constants.AppConstants;
import com.harishjose.myitemdelivery.constants.FragmentTags;
import com.harishjose.myitemdelivery.databinding.FragmentDeliveriesListBinding;
import com.harishjose.myitemdelivery.models.Item;
import com.harishjose.myitemdelivery.utils.GeneralUtil;
import com.harishjose.myitemdelivery.views.MainActivity;
import com.harishjose.myitemdelivery.views.common.BaseFragment;

import java.util.ArrayList;

/**
 * Created by harish.jose on 02-09-2018.
 */
public class DeliveriesListFragment extends BaseFragment implements DeliveriesListContract.DeliveriesListView{

    private FragmentDeliveriesListBinding binding;
    private DeliveryItemAdapter adapter;
    private ArrayList<Item> deliveriesArrayList;
    private DeliveriesListPresenter presenter;
    private Bundle bundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentDeliveriesListBinding.inflate(inflater, container, false);
        init();
        return binding.getRoot();
    }

    @Override
    protected void init() {
        presenter = new DeliveriesListPresenter();
        presenter.setView(this);
        deliveriesArrayList = new ArrayList<>();
        adapter = new DeliveryItemAdapter(deliveriesArrayList, new OnClickPositionCallback() {
            @Override
            public void onClick(int position) {
                bundle.putSerializable(AppConstants.DELIVERY_ITEM, deliveriesArrayList.get(position));
                ((MainActivity)getActivity()).loadFragment(FragmentTags.ITEM_DETAILS_FRAGMENT, bundle);
            }
        });
        binding.listDeliveries.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.listDeliveries.setAdapter(adapter);
        presenter.fetchDeliveriesList();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).setToolbarTitle("Things To Deliver");
    }

    @Override
    public void setDeliveriesList(ArrayList<Item> dataList) {
        this.deliveriesArrayList.clear();
        this.deliveriesArrayList.addAll(dataList);
        if(this.deliveriesArrayList.size() == 0) {
            binding.tvNoData.setVisibility(View.VISIBLE);
        } else {
            binding.tvNoData.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String message) {
        GeneralUtil.showLongToast(getActivity(), message);
    }

    @Override
    public void hideShimmer(boolean isVisible) {
        binding.shimmerLyt.stopShimmer();
        if(!isVisible) {
            binding.shimmerLyt.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
