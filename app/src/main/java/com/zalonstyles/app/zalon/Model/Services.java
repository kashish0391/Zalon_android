package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 15-07-2016.
 */
public class Services
{
    private String name = null;
    private boolean checked = false;
    private int categotry_id;
    private String status= null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCategotry_id() {
        return categotry_id;
    }

    public void setCategotry_id(int categotry_id) {
        this.categotry_id = categotry_id;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    private String catname=null;

    public Services()
    {
    }

    public Services(String name)
    {
        this.name = name;
    }

    public Services(String name, boolean checked, int categotry_id,String catname)
{
    this.name = name;
    this.categotry_id = categotry_id;
    this.checked = checked;
    this.catname = catname;
}
    public Services(String name, int categotry_id)
    {
        this.name = name;
        this.categotry_id = categotry_id;

    }
    public int getCategory_id()
    {
        return categotry_id;
    }

    public void setcategotry_id(int categotry_id)
    {
        this.categotry_id = categotry_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public String toString()
    {
        return name;
    }

    public void toggleChecked()
    {
        checked = !checked;
    }
}
