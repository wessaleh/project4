package com.example.project4;

import javafx.collections.FXCollections;
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
import java.util.ArrayList;

import static com.example.project4.PizzaMaker.createPizza;

/**
 * Primary stage controller class
 * @author Wesam Saleh
 */

public class MainController {

    protected final Order currentOrder = new Order();
    protected final StoreOrders storeOrders = new StoreOrders();
    private Alert popup;

    @FXML
    protected TextField phoneNumber;

    /**
     * Gets the total of all pizzas in the current order
     * @return - order subtotal
     */
    public double getSubTotal() {
        double total = 0;

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
            return number.length() == 10;
        }catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Shows pop up for current order window
     * @throws IOException - if the fxml file doesn't exist
     */
    @FXML
    void viewCurrentOrder() throws IOException {
        if(phoneNumber.getText().isEmpty()){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Phone number");
            popup.setContentText("Please provide a phone number.");
            popup.showAndWait();
            return;
        }

        if(!validatePhoneNumber(phoneNumber.getText())){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Phone number");
            popup.setContentText("Invalid phone number.");
            popup.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("currentorder-view.fxml"));
        Parent root = loader.load();
        CurrentOrderController currentOrderView = loader.getController();
        currentOrderView.setMainController(this);

        currentOrderView.phoneNumber.appendText(phoneNumber.getText());
        currentOrderView.cart.setItems(currentOrder.pizzas);
        currentOrderView.setValues();

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Order Detail");
        stage.setScene(scene);
        currentOrderView.stage = stage;
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for Deluxe pizza order
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewDeluxeOrder() throws IOException {
        popup = new Alert(Alert.AlertType.CONFIRMATION);
        popup.setTitle("Ordering Pizzas");
        popup.setContentText("Starting a new order!");
        popup.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Image pizzaImage = new Image("file:./Deluxe.jpeg");
        pizzaView.pizzaImage.setImage(pizzaImage);
        pizzaView.pizzaLabel.setText("Deluxe");
        pizzaView.MIN_TOPPINGS = 5;
        pizzaView.pizza = createPizza("Deluxe");

        pizzaView.DEFAULT_TOPPINGS = FXCollections.observableArrayList(
                Topping.Sausage, Topping.GreenPepper, Topping.Onion, Topping.Pepperoni, Topping.Mushroom);
        pizzaView.setDefaultValues();

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
        popup = new Alert(Alert.AlertType.CONFIRMATION);
        popup.setTitle("Ordering Pizzas");
        popup.setContentText("Starting a new order!");
        popup.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Image pizzaImage = new Image("file:./Hawaiian.jpeg");
        pizzaView.pizzaImage.setImage(pizzaImage);
        pizzaView.pizzaLabel.setText("Hawaiian");
        pizzaView.MIN_TOPPINGS = 2;
        pizzaView.pizza = createPizza("Hawaiian");

        pizzaView.DEFAULT_TOPPINGS = FXCollections.observableArrayList(Topping.Pineapple, Topping.Cheese);
        pizzaView.setDefaultValues();

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
        popup = new Alert(Alert.AlertType.CONFIRMATION);
        popup.setTitle("Ordering Pizzas");
        popup.setContentText("Starting a new order!");
        popup.showAndWait();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Image pizzaImage = new Image("file:./Pepperoni.jpeg");
        pizzaView.pizzaImage.setImage(pizzaImage);
        pizzaView.pizzaLabel.setText("Pepperoni");
        pizzaView.MIN_TOPPINGS = 1;
        pizzaView.pizza = createPizza("Pepperoni");

        pizzaView.DEFAULT_TOPPINGS = FXCollections.observableArrayList(Topping.Pepperoni);
        pizzaView.setDefaultValues();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storeorder-view.fxml"));
        Parent root = loader.load();
        StoreOrderController storeOrderView = loader.getController();
        storeOrderView.setMainController(this);

        for(Order order: storeOrders.orders) {
            storeOrderView.phoneNumbers.getItems().add(order.phoneNumber);
        }

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Store Orders");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}