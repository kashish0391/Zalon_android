package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 27-09-2016.
 */
public class Customersrch {
    private String name;
    private String mob;
    private String gender;
    public Customersrch(String name, String mob, String gender) {
        this.name = name;
        this.mob = mob;
        this.gender = gender;
    }
    public Customersrch(){}

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }
}
