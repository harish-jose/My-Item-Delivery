package com.harishjose.myitemdelivery.views;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.harishjose.myitemdelivery.R;
import com.harishjose.myitemdelivery.constants.FragmentTags;
import com.harishjose.myitemdelivery.utils.FragmentNavigationManager;
import com.harishjose.myitemdelivery.views.deliveryList.DeliveriesListFragment;
import com.harishjose.myitemdelivery.views.itemDetail.ItemDetailsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(FragmentTags.DELIVERIES_LIST_FRAGMENT, null);
    }

    /**
     * Function which will load the fragment from a tag name
     *
     * @param tag
     */
    public void loadFragment(FragmentTags tag, Bundle bundle) {
        //set background colour for fragment container
        boolean isBackStackRequired = false;
        Fragment fragment = null;
        switch (tag) {
            case DELIVERIES_LIST_FRAGMENT:
                fragment = new DeliveriesListFragment();
                break;
            case ITEM_DETAILS_FRAGMENT:
                fragment = new ItemDetailsFragment();
                isBackStackRequired = true;
                break;

        }
        fragment.setArguments(bundle);
        FragmentNavigationManager.replaceFragment(this, fragment, getSupportFragmentManager(), R.id.frame_main_content,
                tag.toString(), isBackStackRequired);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void setToolbarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}
