package com.example.naseem.pdemo;

/**
 * Created by User on 05-Jun-18.
 */

public class CatModel {
    String slugs;
    String name, imageUrl;
    String image_path="http://ae.priceomania.com/mobileAppImage/categoryIcon/";

    public CatModel(String slugs, String name, String imageUrl) {
        this.slugs = slugs;
        this.name = name;
        this.imageUrl =image_path+imageUrl;
    }

    public String getSlugs() {
        return slugs;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
