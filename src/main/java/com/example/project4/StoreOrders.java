package com.example.project4;

import java.util.ArrayList;

/**
 * Store Orders class for keeping track of orders and exporting them
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class StoreOrders {
    protected ArrayList<Order> orders;

    /**
     * Constructor for store orders class
     */
    public StoreOrders(){
        this.orders = new ArrayList<>();
    }
}
