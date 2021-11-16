package com.example.project4;

/**
 * Pizza Maker class
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class PizzaMaker {

    /**
     * Creates an instance of pizza
     * @param flavor - the flavor of pizza
     * @return the pizza
     */
    public static Pizza createPizza(String flavor){
        return switch (flavor) {
            case "Hawaiian" -> new Hawaiian();
            case "Deluxe" -> new Deluxe();
            case "Pepperoni" -> new Pepperoni();
            default -> null;
        };

    }
}
