package thinkbiz.solutions.tbs.com.MoreSites;

/**
 * Created by User on 25-Apr-18.
 */

public class CardModel {


    private String websiteID;
    private String mobileName;
    private String siteName;
    private String sitelogoUrl;
    private String mobileCurrency;
    private String mobilePrice;
    private String siteurl;
    String image_path="http://ae.priceomania.com/upload/ProductImage/thumb/";
    //, String websiteUrl

    public CardModel( String websiteID,String mobileName, String siteName, String sitelogoUrl, String mobileCurrency, String mobilePrice,String siteurl) {
        this.websiteID=websiteID;
        this.mobileName = mobileName;
        this.siteName = siteName;
        this.sitelogoUrl = image_path+sitelogoUrl;
        this.mobileCurrency = mobileCurrency;
        this.mobilePrice = mobilePrice;
        this.siteurl=siteurl;
        //this.websiteUrl = websiteUrl;
    }
    public String getWebsiteID() {
        return websiteID;
    }

    public void setWebsiteID(String websiteID) {
        this.websiteID = websiteID;
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

    public String getSitelogoUrl() {
        return sitelogoUrl;
    }

    public void setSitelogoUrl(String sitelogoUrl) {
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

    public String getSiteurl() {
        return siteurl;
    }

    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }
}
