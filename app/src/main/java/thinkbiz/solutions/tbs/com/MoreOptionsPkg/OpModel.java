package thinkbiz.solutions.tbs.com.MoreOptionsPkg;

/**
 * Created by User on 21-May-19.
 */

public class OpModel {

    private String mobileName;
    private String siteName;
    private int sitelogoUrl;
    private String mobileCurrency;
    private String mobilePrice;
   // private String siteurl;
    String image_path="http://ae.priceomania.com/upload/ProductImage/thumb/";
    //, String websiteUrl

    public OpModel( String mobileName, String siteName, String mobileCurrency, String mobilePrice,int sitelogoUrl) {

        this.mobileName = mobileName;
        this.siteName = siteName;
        this.mobileCurrency = mobileCurrency;
        this.mobilePrice = mobilePrice;
        this.sitelogoUrl = sitelogoUrl;
    }


    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public int getSitelogoUrl() {
        return sitelogoUrl;
    }

    public void setSitelogoUrl(int sitelogoUrl) {
        this.sitelogoUrl = sitelogoUrl;
    }

    public String getMobileCurrency() {
        return mobileCurrency;
    }

    public void setMobileCurrency(String mobileCurrency) {
        this.mobileCurrency = mobileCurrency;
    }

    public String getMobilePrice() {
        return mobilePrice;
    }

    public void setMobilePrice(String mobilePrice) {
        this.mobilePrice = mobilePrice;
    }

//    public String getSiteurl() {
//        return siteurl;
//    }
//
//    public void setSiteurl(String siteurl) {
//        this.siteurl = siteurl;
//    }
}
