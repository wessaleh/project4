package com.example.project4;

import java.util.ArrayList;

/**
 * Pizza class
 * @author Wesam Saleh
 */

public abstract class Pizza {
    public final double MEDIUM_UPCHARGE = 2.00;
    public final double LARGE_UPCHARGE = 4.00;
    public final double EXTRA_TOPPINGS_CHARGE = 1.49;
    public static final int NUM_DECIMAL_PLACES = 2;
    public static final int NUM_INT_PLACES = 1;

    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;

    /**
     * Calculates the price of the pizza
     * @return - the price of the pizza
     */
    public abstract double price();
}
