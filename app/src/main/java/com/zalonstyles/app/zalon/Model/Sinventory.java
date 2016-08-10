package com.zalonstyles.app.zalon.Model;

/**
 * Created by KASHISH on 02-08-2016.
 */

   public class Sinventory {
        private String category;
        private String name;
        private String stock;
        private String price;
        private boolean clicked = false;


    public Sinventory(){

        }
        public Sinventory(String name)
        {
            this.name = name;
        }

        public Sinventory(String name, String category, String stock, String price)
        {
            this.name = name;
            this.category = category;
            this.stock =stock;
            this.price = price;

        }
        public Sinventory(String name, String category, String stock, String price, Boolean clicked)
        {
            this.name = name;
            this.category = category;
            this.stock =stock;
            this.price = price;
            this.clicked =clicked;

        }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }
        public void setClicked(boolean Clicked)
        {
            this.clicked = Clicked;
        }

        public void setCategory(String category) {
            this.category = category;
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


