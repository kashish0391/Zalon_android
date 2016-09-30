package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 20-09-2016.
 */
public class custommarket {
    private String rules;
    private String flist;
    private boolean clicked = false;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFlist() {
        return flist;
    }

    public void setFlist(String flist) {
        this.flist = flist;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }


//    public emplyeeModel(String rules, String flist,String value, Boolean clicked)
//    {
//        this.rules = rules;
//        this.flist = flist;
//        this.value= value;
//        this.clicked =clicked;
//
//    }

    public void setClicked(boolean Clicked)
    {
        this.clicked = Clicked;
    }

    public boolean isClicked()
    {
        return clicked;
    }

    public void toggleClicked()
    {
        clicked = !clicked;
    }
}
