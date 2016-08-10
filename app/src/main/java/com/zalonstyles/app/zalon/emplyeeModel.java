package com.zalonstyles.app.zalon;

/**
 * Created by KASHISH on 02-08-2016.
 */
public class emplyeeModel {
    private String name;
    private String number;
    private boolean clicked = false;
    public emplyeeModel(){

    }
    public emplyeeModel(String name)
    {
        this.name = name;
    }

    public emplyeeModel(String name, String number)
    {
        this.name = name;
        this.number = number;

    }
    public emplyeeModel(String name, String number, Boolean clicked)
    {
        this.name = name;
        this.number = number;
        this.clicked =clicked;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }
    public void setClicked(boolean Clicked)
    {
        this.clicked = Clicked;
    }

    public void setNumber(String number) {
        this.number = number;
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
