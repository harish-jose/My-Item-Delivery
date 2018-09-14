package com.harishjose.myitemdelivery.views.itemDetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.harishjose.myitemdelivery.constants.AppConstants;
import com.harishjose.myitemdelivery.databinding.FragmentItemDetailBinding;
import com.harishjose.myitemdelivery.models.Item;
import com.harishjose.myitemdelivery.utils.GeneralUtil;
import com.harishjose.myitemdelivery.views.MainActivity;
import com.harishjose.myitemdelivery.views.common.BaseFragment;

/**
 * Created by harish.jose on 14-09-2018.
 */
public class ItemDetailsFragment extends BaseFragment implements OnMapReadyCallback {
    private GoogleMap gmap;
    private FragmentItemDetailBinding binding;
    private Item item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentItemDetailBinding.inflate(inflater, container, false);
        init();
        return binding.getRoot();
    }

    @Override
    protected void init() {
        ((MainActivity)getActivity()).setToolbarTitle("Delivery Details");
        if(getArguments() != null) {
            item = (Item) getArguments().getSerializable(AppConstants.DELIVERY_ITEM);
            binding.tvDescription.setText(item.getDescription());
            binding.tvAddress.setText(item.getLocation().getAddress());
            GeneralUtil.loadFrescoImage(binding.imgItem, item.getImageUrl());
        }
        binding.mapView.getMapAsync(this);
        binding.mapView.onCreate(null);
        binding.mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(item.getLocation().getLat(), item.getLocation().getLng())).title(item.getLocation().getAddress());
        // Changing marker icon
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        // adding marker
        gmap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(item.getLocation().getLat(), item.getLocation().getLng())).zoom(16).build();
        gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
