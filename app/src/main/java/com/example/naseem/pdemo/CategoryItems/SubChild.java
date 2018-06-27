package com.example.naseem.pdemo.CategoryItems;

/**
 * Created by User on 24-May-18.
 */

public class SubChild  {

    String id;
    String name, imageUrl;
    String image_path="http://ae.priceomania.com/mobileAppImage/categoryIcon/";


    public SubChild(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl =image_path+imageUrl;


    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
