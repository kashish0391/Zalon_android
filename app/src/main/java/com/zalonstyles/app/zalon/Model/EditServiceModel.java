package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 19-08-2016.
 */
public class EditServiceModel {


    private String name = null;
    private boolean checked = false;
    private int categotry_id;
    private String categoryName = null;


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategotry_id() {
        return categotry_id;
    }

    public void setCategotry_id(int categotry_id) {
        this.categotry_id = categotry_id;
    }


    public EditServiceModel() {
    }

    public EditServiceModel(String name) {
        this.name = name;
    }

    public EditServiceModel(String name, boolean checked, int categotry_id) {
        this.name = name;
        this.categotry_id = categotry_id;
        this.checked = checked;
    }

    public EditServiceModel(String name, int categotry_id) {
        this.name = name;
        this.categotry_id = categotry_id;

    }

    public int getCategory_id() {
        return categotry_id;
    }

    public void setcategotry_id(int categotry_id) {
        this.categotry_id = categotry_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String toString() {
        return name;
    }

    public void toggleChecked() {
        checked = !checked;
    }
}




