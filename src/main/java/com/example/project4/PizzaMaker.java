package com.example.project4;

public class PizzaMaker {
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
