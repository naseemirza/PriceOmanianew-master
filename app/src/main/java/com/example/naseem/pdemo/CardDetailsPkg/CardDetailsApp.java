package com.example.naseem.pdemo.CardDetailsPkg;

/**
 * Created by User on 3/12/2018.
 */

public class CardDetailsApp {


    private String mName,mSite,mPrice;
    private int mImage;


    public CardDetailsApp(String name,String price,String site, int image ){
        mName=name;
        mPrice=price;
        mSite=site;
        mImage=image;

    }
    public String getPrice(){
        return mPrice;
    }
    //  public int getDrawable(){ return mDrawable;}
    public String getName(){return mName;}
    public String getSite(){return mSite;}
    public int getImage(){return mImage;}
}
