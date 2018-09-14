package com.harishjose.myitemdelivery.views.common;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;


/**
 * Created by harish.jose on 02-09-2018.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    protected abstract void init();


}
