package thinkbiz.solutions.tbs.com.CategoryItems;

/**
 * Created by User on 23-May-18.
 */

public class GridModel {
    private String mID;
    private String mImageUrl;
    private String mName;
    private String mCurrency;
    private String mPrice;
    private String mCount;
    private String slug;
    private String slug_suffix;

    String image_path = "http://ae.priceomania.com/backend/ProductImage/";

    public GridModel(String mID, String mName,String mImageUrl, String mCurrency, String mPrice, String mCount,String slug,String slug_suffix) {
        this.mID = mID;
        this.mName = mName;
        this.mImageUrl =image_path+mImageUrl;
        this.mCurrency = mCurrency;
        this.mPrice = mPrice;
        this.mCount = mCount;
        this.slug = slug;
        this.slug_suffix = slug_suffix;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug_suffix() {
        return slug_suffix;
    }

    public void setSlug_suffix(String slug_suffix) {
        this.slug_suffix = slug_suffix;
    }
}
