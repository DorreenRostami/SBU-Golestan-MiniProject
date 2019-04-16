package Model;

import java.io.Serializable;

public class Food implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int price;
    private Day day = null;

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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}