package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 17-11-2016.
 */

public class servicesrch  {
    private String id;
    private String type;
    private String name;
    public servicesrch(String name, String type, String id) {
        this.name = name;
        this.type = type;
        this.id = id;
    }
    public servicesrch(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
