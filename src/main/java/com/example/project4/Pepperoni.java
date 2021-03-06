package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Pepperoni pizza class
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Pepperoni extends Pizza{
    private static final double DEFAULT_PRICE = 8.99;
    private static final double ESSENTIAL_TOPPINGS = 1;

    /**
     * Default constructor
     */
    public Pepperoni(){
        this.toppings = new ArrayList<Topping>();
        this.size = Size.Small;
    }

    /**
     * Constructor for pepperoni pizza
     * @param toppings - toppings of pizza
     * @param size - the size of the pizza
     */
    public Pepperoni(ArrayList<Topping> toppings, Size size){
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

        // accounting for number of toppings
        if(numToppings > 1){
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

        StringBuilder sb = new StringBuilder("Pepperoni pizza, ");

        for(Topping topping: toppings){
            sb.append(topping + ", ");
        }

        sb.append(money_Format.format(this.price()));
        return sb.toString();
    }

    @Override
    public Pizza copy() {
        Pizza clone = new Pepperoni(new ArrayList<Topping>(this.toppings), this.size);
        return clone;
    }
}
