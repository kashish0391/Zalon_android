package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 21-10-2016.
 */

public class campaign {
    private String name = null;
    private String status= null;
    public campaign(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public campaign(String name, String status)
    {
        this.name = name;

        this.status = status;
    }
}
