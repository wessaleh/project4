package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.project4.PizzaMaker.createPizza;

public class MainController {
    @FXML
    protected TextField phoneNumber;

    /**
     * Shows pop up for current order window
     * @param event - the event to handle
     * @throws IOException - if the fxml file doesn't exist
     */
    @FXML
    void viewCurrentOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("currentorder-view.fxml"));
        Parent root = (Parent) loader.load();
        CurrentOrderController currentOrderView = loader.getController();
        currentOrderView.setMainController(this);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Order Detail");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for Deluxe pizza order
     * @param event - the event to handle
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewDeluxeOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = (Parent) loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Image pizzaImage = new Image("file:./Deluxe.jpeg");
        pizzaView.pizzaImage.setImage(pizzaImage);
        pizzaView.pizzaLabel.setText("Deluxe");
        pizzaView.MIN_TOPPINGS = 5;
        pizzaView.pizza = createPizza("Deluxe");

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for Hawaiian pizza order
     * @param event - the event to handle
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewHawaiianOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = (Parent) loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Image pizzaImage = new Image("file:./Hawaiian.jpeg");
        pizzaView.pizzaImage.setImage(pizzaImage);
        pizzaView.pizzaLabel.setText("Hawaiian");
        pizzaView.MIN_TOPPINGS = 2;
        pizzaView.pizza = createPizza("Hawaiian");

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for Pepperoni pizza order
     * @param event - the event to handle
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewPepperoniOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = (Parent) loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Image pizzaImage = new Image("file:./Pepperoni.jpeg");
        pizzaView.pizzaImage.setImage(pizzaImage);
        pizzaView.pizzaLabel.setText("Pepperoni");
        pizzaView.MIN_TOPPINGS = 1;
        pizzaView.pizza = createPizza("Pepperoni");

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Shows pop up for store orders
     * @param event - the event to handle
     * @throws IOException - if the fxml doesn't exist
     */
    @FXML
    void viewStoreOrders(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("storeorder-view.fxml"));
        Parent root = (Parent) loader.load();
        StoreOrderController storeOrderView = loader.getController();
        storeOrderView.setMainController(this);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Store Orders");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}