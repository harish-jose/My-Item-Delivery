package com.harishjose.myitemdelivery.constants;

/**
 * Created by harish on 14-09-18.
 */

public enum FragmentTags {
    DELIVERIES_LIST_FRAGMENT("DeliveriesListFragment"),
    ITEM_DETAILS_FRAGMENT("ItemDetailsFragment");

    private final String text;

    /**
     * @param text string value
     */
    private FragmentTags(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static FragmentTags getTagFromString(String code){
        try{
            for(FragmentTags e : FragmentTags.values()){
                if(code.equals(e.text)) return e;
            }
        }
        catch (Exception e){

        }

        return null;
    }


}
