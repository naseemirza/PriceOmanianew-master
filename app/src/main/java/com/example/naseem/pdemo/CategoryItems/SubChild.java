package com.example.naseem.pdemo.CategoryItems;

/**
 * Created by User on 24-May-18.
 */

public class SubChild  {


    String id;
    String name, imageUrl;
    String image_path="http://ae.priceomania.com/mobileAppImage/categoryIcon/";
    String categ_type;

    public SubChild(String id, String name, String imageUrl,String categ_type) {
        this.id = id;
        this.name = name;
        this.imageUrl =image_path+imageUrl;
        this.categ_type=categ_type;

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
    public String getCateg_type() {
        return categ_type;
    }
}
