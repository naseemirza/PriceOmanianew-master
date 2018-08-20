package thinkbiz.solutions.tbs.com;

/**
 * Created by User on 05-Jun-18.
 */

public class CatModel {
    String id;
    String name, imageUrl;
    String image_path="http://ae.priceomania.com/mobileAppImage/categoryIcon/";

    public CatModel(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl =image_path+imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
