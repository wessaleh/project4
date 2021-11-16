package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Primary stage controller class
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class MainController {

    private final Order currentOrder = new Order();
    private final StoreOrders storeOrders = new StoreOrders();
    private Alert popup;

    @FXML
    private TextField phoneNumber;

    /**
     * Gets the total of all pizzas in the current order
     * @return - order subtotal
     */
    public double getSubTotal() {
        double total = 0;

        // adding up all pizza costs
        for(Pizza pizza: currentOrder.pizzas){
            total += pizza.price();
        }

        return total;
    }

    /**
     * Validates a phone number making sure it is a 10-digit number
     * @param number - the number to validate
     * @return true if valid, false if not
     */
    public static boolean validatePhoneNumber(String number){
        try{
            Long.parseLong(number);
            return number.length() == 10; // phone number must be 10 digits long
        }catch(NumberFormatException e){
            return false; // not a valid phone number with 10 digits
        }
    }

    /**
     * Shows pop up for current order window
     * @throws IOException - if the fxml file doesn't exist
     */
    @FXML
    void viewCurrentOrder() throws IOException {
        // No phone number provided
        if(phoneNumber.getText().isEmpty()){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Phone number");
            popup.setContentText("Please provide a phone number.");
            popup.showAndWait();
            return;
        }

        // phone number is invalid
        if(!validatePhoneNumber(phoneNumber.getText())){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Phone number");
            popup.setContentText("Invalid phone number.");
            popup.showAndWait();
            return;
        }

        // loading current order view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("currentorder-view.fxml"));
        Parent root = loader.load();
        CurrentOrderController currentOrderView = loader.getController();
        currentOrderView.setMainController(this);

        // setting the phone number and loading the current order
        currentOrderView.setPhoneNumberText(phoneNumber.getText());
        currentOrderView.setItemsInCart(currentOrder.pizzas);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Order Detail");
        stage.setScene(scene);
        currentOrderView.setValues(stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for Deluxe pizza order
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewDeluxeOrder() throws IOException {
        // alerting the user that new order is being added
        popup = new Alert(Alert.AlertType.CONFIRMATION);
        popup.setTitle("Ordering Pizzas");
        popup.setContentText("Starting a new order!");
        popup.showAndWait();

        // loading pizza order view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        // setting the right image and text
        Image pizzaImage = new Image("file:./Deluxe.jpeg");
        // setting default toppings
        ObservableList<Topping> toppings = FXCollections.observableArrayList(
                Topping.Sausage, Topping.GreenPepper, Topping.Onion, Topping.Pepperoni, Topping.Mushroom);

        int minToppings = 5;

        pizzaView.setDefaultValues(pizzaImage, "Deluxe", minToppings, toppings);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for Hawaiian pizza order
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewHawaiianOrder() throws IOException {
        // alerting the user that new order is being added
        popup = new Alert(Alert.AlertType.CONFIRMATION);
        popup.setTitle("Ordering Pizzas");
        popup.setContentText("Starting a new order!");
        popup.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        // setting the right image and text
        Image pizzaImage = new Image("file:./Hawaiian.jpeg");
        // setting default toppings
        ObservableList<Topping> toppings = FXCollections.observableArrayList(Topping.Pineapple, Topping.Cheese);
        int minToppings = 2;

        pizzaView.setDefaultValues(pizzaImage, "Hawaiian", minToppings, toppings);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for Pepperoni pizza order
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewPepperoniOrder() throws IOException {
        // alerting the user that new order is being added
        popup = new Alert(Alert.AlertType.CONFIRMATION);
        popup.setTitle("Ordering Pizzas");
        popup.setContentText("Starting a new order!");
        popup.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        // setting the right image and text
        Image pizzaImage = new Image("file:./Pepperoni.jpeg");

        // setting default toppings
        ObservableList<Topping> toppings = FXCollections.observableArrayList(Topping.Pepperoni);
        int minToppings = 1;

        pizzaView.setDefaultValues(pizzaImage, "Pepperoni", minToppings, toppings);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for store orders
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewStoreOrders() throws IOException {
        // loading store order view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storeorder-view.fxml"));
        Parent root = loader.load();
        StoreOrderController storeOrderView = loader.getController();
        storeOrderView.setMainController(this);

        // loading the order phone numbers for user to select
        for(Order order: storeOrders.orders) {
            storeOrderView.addPhoneNumberToList(order.phoneNumber);
        }

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Store Orders");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * adds pizza to current order
     * @param pizza - pizza to add
     */
    public void addPizzaToCurrentOrder(Pizza pizza){
        currentOrder.pizzas.add(pizza);
    }

    /**
     * Adds an order to the store orders
     * @param order - order to add
     */
    public void addOrderToStoreOrders(Order order){
        storeOrders.orders.add(order);
    }

    /**
     * Clears the current order
     */
    public void clearOrder() {
        currentOrder.pizzas.removeAll(currentOrder.pizzas);
        phoneNumber.clear();
    }

    /**
     * Removes a pizza from current order
     * @param pizza - the pizza to remove
     */
    public void removePizzaFromOrder(Pizza pizza){
        currentOrder.pizzas.remove(pizza);
    }

    /**
     * Gets the current order
     * @return - the current order
     */
    public ObservableList<Pizza> getCurrentOrderPizzas() {
        return currentOrder.pizzas;
    }

    /**
     * Gets the store orders
     * @return - the store order
     */
    public StoreOrders getStoreOrder() {
        return storeOrders;
    }

}