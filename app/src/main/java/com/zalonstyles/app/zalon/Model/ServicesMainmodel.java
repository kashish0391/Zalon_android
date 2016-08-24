package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 16-08-2016.
 */
public class ServicesMainmodel {

    private String name = null;
    private String status = null;
    private int categotry_id;
    private String Service_id;

    public void setCategotry_id(int categotry_id) {
        this.categotry_id = categotry_id;
    }

    public String getService_id() {
        return Service_id;
    }

    public void setService_id(String service_id) {
        Service_id = service_id;
    }

    public ServicesMainmodel() {
    }

    public int getCategotry_id() {
        return categotry_id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ServicesMainmodel(String name) {
        this.name = name;
    }

    public ServicesMainmodel(String name, String status, int categotry_id) {
        this.name = name;
        this.categotry_id = categotry_id;
        this.status = status;
    }

    public ServicesMainmodel(String name, int categotry_id) {
        this.name = name;
        this.categotry_id = categotry_id;

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


    public String toString() {
        return name;
    }


}
