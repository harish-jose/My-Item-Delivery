package com.harishjose.myitemdelivery.views.deliveryList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.harishjose.myitemdelivery.R;
import com.harishjose.myitemdelivery.callbacks.OnClickPositionCallback;
import com.harishjose.myitemdelivery.models.Item;
import com.harishjose.myitemdelivery.utils.GeneralUtil;

import java.util.ArrayList;


/**
 * Created by harish.jose on 14-09-2018.
 */

public class DeliveryItemAdapter extends RecyclerView.Adapter<DeliveryItemAdapter.DeliveryItemHolder>{
    private ArrayList<Item> itemArrayList;
    private OnClickPositionCallback callback;

    public DeliveryItemAdapter(ArrayList<Item> dataList, OnClickPositionCallback positionCallback){
        this.itemArrayList = dataList;
        this.callback = positionCallback;
    }

    @NonNull
    @Override
    public DeliveryItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_deliveries_item, parent, false);
        return new DeliveryItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryItemHolder holder, final int position) {
        holder.tvItemDescription.setText(itemArrayList.get(position).getDescription());
        holder.tvItemAddress.setText(itemArrayList.get(position).getLocation().getAddress());

        GeneralUtil.loadFrescoImage(holder.imgItem, itemArrayList.get(position).getImageUrl());
        holder.lytItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList != null ? itemArrayList.size() : 0;
    }

    class DeliveryItemHolder extends RecyclerView.ViewHolder{
        TextView tvItemDescription, tvItemAddress;
        SimpleDraweeView imgItem;
        RelativeLayout lytItem;
        public DeliveryItemHolder(View view){
            super(view);
            tvItemDescription = view.findViewById(R.id.tv_description);
            tvItemAddress = view.findViewById(R.id.tv_address);
            imgItem = view.findViewById(R.id.img_item);
            lytItem = view.findViewById(R.id.lyt_item);
        }
    }
}
