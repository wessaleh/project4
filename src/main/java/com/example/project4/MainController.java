package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private TextField phoneNumber;

    @FXML
    void viewCurrentOrder(ActionEvent event) {

    }

    @FXML
    void viewDeluxeOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = (Parent) loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    void viewHawaiianOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = (Parent) loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    void viewPepperoniOrder(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("pizza-view.fxml"));
        Parent root = (Parent) loader.load();
        PizzaController pizzaView = loader.getController();
        pizzaView.setMainController(this);

        Stage stage = new Stage();
        Scene scene = new Scene(root, 430, 516);
        stage.setTitle("Customize your Pizza");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    void viewStoreOrders(ActionEvent event) {

    }

}