package Model;

import java.io.Serializable;

public class Food implements Serializable {
    private String name;
    private int price;
    public Day day = null;

    public Food(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}