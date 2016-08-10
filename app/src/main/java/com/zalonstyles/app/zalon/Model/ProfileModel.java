package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 06-08-2016.
 */
public class ProfileModel {
    private String name;
    private String profilemodel;
    private String mobile;
    private String Address;
    private String email;
    private String website;
    private String Paymentmethod;
    private String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getProfilemodel() {
        return profilemodel;
    }

    public void setProfilemodel(String profilemodel) {
        this.profilemodel = profilemodel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPaymentmethod() {
        return Paymentmethod;
    }

    public void setPaymentmethod(String paymentmethod) {
        Paymentmethod = paymentmethod;
    }

    private String time;
    public ProfileModel(){

    }
    public ProfileModel(String name)
    {
        this.name = name;
    }

    public ProfileModel(String name, String time,String website,String email,String mobile,String profilemodel,String paymentmethod,String logo,String address )
    {
        this.name = name;
        this.time = time;
        this.website = website;
        this.email= email;
        this.mobile = mobile;
        this.profilemodel = profilemodel;
        this.Paymentmethod =paymentmethod;
        this.logo = logo;
        this.Address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
