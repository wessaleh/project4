package com.example.project4;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Hawaiian pizza class
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Hawaiian extends Pizza{
    private static final double DEFAULT_PRICE = 10.99;
    private static final int ESSENTIAL_TOPPINGS = 2;

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

        // accounting for pizza size
        if(this.size == Size.Medium){
            totalPrice += MEDIUM_UPCHARGE;
        }else if(this.size == Size.Large){
            totalPrice += LARGE_UPCHARGE;
        }

        // accounting for number of toppings
        if(numToppings > 2){
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

        StringBuilder sb = new StringBuilder("Hawaiian pizza, ");

        for(Topping topping: toppings){
            sb.append(topping + ", ");
        }

        sb.append(money_Format.format(this.price()));
        return sb.toString();
    }

    @Override
    public Pizza copy() {
        Pizza clone = new Hawaiian(new ArrayList<Topping>(this.toppings), this.size);
        return clone;
    }
}
