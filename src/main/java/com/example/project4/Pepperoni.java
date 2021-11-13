package com.example.project4;

import java.util.ArrayList;

public class Pepperoni extends Pizza{
    private final double DEFAULT_PRICE = 8.99;

    public Pepperoni(){
        this.toppings = new ArrayList<Topping>();
        this.size = Size.Small;
    }

    public Pepperoni(ArrayList<Topping> toppings, Size size){
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

        if(numToppings > 1){
            totalPrice += EXTRA_TOPPINGS_CHARGE * (MAX_TOPPINGS - numToppings);
        }

        return totalPrice;
    }
}
