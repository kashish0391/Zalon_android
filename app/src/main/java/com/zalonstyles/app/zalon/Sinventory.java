package com.zalonstyles.app.zalon;

/**
 * Created by KASHISH on 21-07-2016.
 */
public class Sinventory {
    private String name;
    private String number;
    private boolean clicked = false;
    public Sinventory(){

    }
    public Sinventory(String name)
    {
        this.name = name;
    }

    public Sinventory(String name, String number)
    {
        this.name = name;
        this.number = number;

    }
    public Sinventory(String name, String number, Boolean clicked)
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
