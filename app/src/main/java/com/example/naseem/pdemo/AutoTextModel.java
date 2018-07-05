package com.example.naseem.pdemo;

/**
 * Created by User on 04-Jul-18.
 */

public class AutoTextModel {

    private String mID;
    private String mImageUrl;
    private String mName;
    private String mCurrency;
    private String mPrice;
    private String mCount;

    String image_path = "http://ae.priceomania.com/backend/ProductImage/";

    public AutoTextModel(String mID, String mImageUrl, String mName, String mCurrency, String mPrice, String mCount) {
        this.mID = mID;
        this.mImageUrl = image_path+mImageUrl;
        this.mName = mName;
        this.mCurrency = mCurrency;
        this.mPrice = mPrice;
        this.mCount = mCount;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
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

    public String getmCount() {
        return mCount;
    }

    public void setmCount(String mCount) {
        this.mCount = mCount;
    }


}
