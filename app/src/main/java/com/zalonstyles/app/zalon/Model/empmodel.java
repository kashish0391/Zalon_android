package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 01-09-2016.
 */
public class empmodel {
    private String name;
    private String category;
    private String id;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
