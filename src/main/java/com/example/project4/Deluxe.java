package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Deluxe pizza class
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Deluxe extends Pizza{
    private final double DEFAULT_PRICE = 12.99;
    private final int ESSENTIAL_TOPPINGS = 5;

    /**
     * Default constructor
     */
    public Deluxe(){
        this.toppings = new ArrayList<Topping>();
        this.size = Size.Small;
    }

    /**
     * Constructor for Deluxe pizza
     * @param toppings - the toppings on the pizza
     * @param size - the size of the pizza
     */
    public Deluxe(ArrayList<Topping> toppings, Size size){
        this.toppings = toppings;
        this.size = size;
    }

    @Override
    public double price(){
        double totalPrice = DEFAULT_PRICE;
        int numToppings = this.toppings.size();

        // accounting for pizza size
        if(this.size == Size.Medium){
            totalPrice += MEDIUM_UPCHARGE;
        }else if(this.size == Size.Large){
            totalPrice += LARGE_UPCHARGE;
        }

        // accounting for toppings
        if(numToppings > 5){
            totalPrice += EXTRA_TOPPINGS_CHARGE * (numToppings - ESSENTIAL_TOPPINGS);
        }

        return totalPrice;
    }

    /**
     * Creates string representation of pizza order
     * @return - the pizza order in string form
     */
    @Override
    public String toString() {
        // setting formatter to print to two decimal places
        DecimalFormat money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(NUM_DECIMAL_PLACES);
        money_Format.setMinimumIntegerDigits(NUM_INT_PLACES);

        StringBuilder sb = new StringBuilder("Deluxe pizza, ");

        for(Topping topping: toppings){
            sb.append(topping + ", ");
        }

        sb.append(money_Format.format(this.price()));
        return sb.toString();
    }

    @Override
    public Pizza copy() {
        Pizza clone = new Deluxe(new ArrayList<Topping>(this.toppings), this.size);
        return clone;
    }
}
