package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 09-09-2016.
 */
public class billlist {
    private String sno;
    private String description;
    private String stylist;
    private String qty;
    private String rate;
    private String amount;
    private String discounts;

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    private boolean clicked = false;
    public billlist(String sno, String description, String stylist, String qty, String rate, String amount, Boolean clicked)
    {
        this.sno = sno;
        this.description = description;
        this.stylist =stylist;
        this.qty = qty;
        this.rate = rate;
        this.amount = amount;
        this.clicked =clicked;

    }
    public billlist(){}


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void toggleClicked()
    {
        clicked = !clicked;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getStylist() {
        return stylist;
    }

    public void setStylist(String stylist) {
        this.stylist = stylist;
    }
}
