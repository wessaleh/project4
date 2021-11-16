package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Order class that holds the pizzas, phone number, and order total for a pizza order
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class Order {
    protected ObservableList<Pizza> pizzas;
    String phoneNumber;
    double orderTotal;

    /**
     * Constructor for order class
     */
    public Order(){
        this.pizzas = FXCollections.observableArrayList();
        this.phoneNumber = "";
        this.orderTotal = 0;
    }
}
