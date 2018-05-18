package com.example.naseem.pdemo.CardDetailsPkg;

/**
 * Created by User on 11-May-18.
 */

public class DetailsApp  {

    private String mImageUrl;
    private String mName;
    private String mCurrency;
    private String mPrice;
    String image_path = "http://ae.priceomania.com/backend/ProductImage/";

    public DetailsApp(String mImageUrl, String mName, String mCurrency, String mPrice) {
        this.mImageUrl =image_path+mImageUrl;
        this.mName = mName;
        this.mCurrency = mCurrency;
        this.mPrice = mPrice;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmCurrency() {
        return mCurrency;
    }

    public void setmCurrency(String mCurrency) {
        this.mCurrency = mCurrency;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }
}
