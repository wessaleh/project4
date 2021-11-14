package com.example.project4;

public class PizzaMaker {

    /**
     * Creates an instance of pizza
     * @param flavor - the flavor of pizza
     * @return the pizza
     */
    public static Pizza createPizza(String flavor){
        if(flavor.equals("Hawaiian")){
            return new Hawaiian();
        }else if(flavor.equals("Deluxe")){
            return new Deluxe();
        }else if(flavor.equals("Pepperoni")){
            return new Pepperoni();
        }

        return null;
    }
}
