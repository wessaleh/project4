package com.example.project4;

import java.util.ArrayList;

public abstract class Pizza {
    public final int MAX_TOPPINGS = 7;
    public final double MEDIUM_UPCHARGE = 2.00;
    public final double LARGE_UPCHARGE = 4.00;
    public final double EXTRA_TOPPINGS_CHARGE = 1.49;

    protected ArrayList<Topping> toppings = new ArrayList<Topping>();
    protected Size size;
    public abstract double price();
}
