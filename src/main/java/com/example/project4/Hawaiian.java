package com.example.project4;

import java.util.ArrayList;

public class Hawaiian extends Pizza{
    private final double DEFAULT_PRICE = 10.99;
    private final int ESSENTIAL_TOPPINGS = 2;

    /**
     * Default constructor for hawaiian pizza
     */
    public Hawaiian(){
        this.toppings = new ArrayList<Topping>();
        this.size = Size.Small;
    }

    /**
     * Constructor for hawaiian pizza
     * @param toppings - toppings on the pizza
     * @param size - size of pizza
     */
    public Hawaiian(ArrayList<Topping> toppings, Size size){
        this.toppings = toppings;
        this.size = size;
    }

    @Override
    public double price(){
        double totalPrice = DEFAULT_PRICE;
        int numToppings = this.toppings.size();

        if(this.size == Size.Medium){
            totalPrice += MEDIUM_UPCHARGE;
        }else if(this.size == Size.Large){
            totalPrice += LARGE_UPCHARGE;
        }

        if(numToppings > 2){
            totalPrice += EXTRA_TOPPINGS_CHARGE * (numToppings - ESSENTIAL_TOPPINGS);
        }

        return totalPrice;
    }
}
