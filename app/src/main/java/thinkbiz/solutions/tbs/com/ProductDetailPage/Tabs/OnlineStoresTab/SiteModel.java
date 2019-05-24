package thinkbiz.solutions.tbs.com.ProductDetailPage.Tabs.OnlineStoresTab;

/**
 * Created by User on 25-Feb-19.
 */

public class SiteModel {

    private String CompName;
    private int mImageUrl;
    private String name;
    private String mCurrency;
    private String mPrice;

    public SiteModel(String compName, int mImageUrl, String name, String mCurrency, String mPrice) {
        CompName = compName;
        this.mImageUrl = mImageUrl;
        this.name = name;
        this.mCurrency = mCurrency;
        this.mPrice = mPrice;
    }

    public String getCompName() {
        return CompName;
    }

    public void setCompName(String compName) {
        CompName = compName;
    }

    public int getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(int mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
