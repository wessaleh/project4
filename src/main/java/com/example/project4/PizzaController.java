package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static com.example.project4.PizzaMaker.createPizza;

public class PizzaController{

    private final int MAX_TOPPINGS = 7;

    protected MainController mainController;
    protected int MIN_TOPPINGS;
    protected Pizza pizza;

    @FXML
    protected ImageView pizzaImage;

    @FXML
    protected Button pizzaLabel;

    @FXML
    private ComboBox<Size> pizzaSize;

    @FXML
    private TextField price;

    @FXML
    protected ListView<Topping> selectedToppings;

    @FXML
    protected ListView<Topping> toppingsList;

    /**
     * Connects this controller to main controller
     * @param controller - the main controller
     */
    public void setMainController(MainController controller){
        this.mainController = controller;
    }


    /**
     * Initializes the pizza ordering stage
     */
    @FXML
    public void initialize() {
        ObservableList<Size> pizzaSizes = FXCollections.observableArrayList(Size.values());
        ObservableList<Topping> toppings = FXCollections.observableArrayList(Topping.values());
        toppingsList.setItems(toppings);
        pizzaSize.setItems(pizzaSizes);

        pizza = new Deluxe();
        pizza.size = Size.Small;
    }

    @FXML
    void addPizzaToOrder(ActionEvent event) {
        // Create the pizza
        // add to main controller's pizza list
    }

    @FXML
    void addTopping(ActionEvent event) {
        if(selectedToppings.getItems().size() == MAX_TOPPINGS){
            return; // ALERT too many toppings
        }

        Topping selectedTopping = toppingsList.getSelectionModel().getSelectedItem();

        if(selectedTopping == null){
            return;
        }

        toppingsList.getItems().remove(selectedTopping);
        selectedToppings.getItems().add(selectedTopping);
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        price.clear();
        price.appendText("" + pizza.price());
    }

    @FXML
    void removeTopping(ActionEvent event) {
        if(selectedToppings.getItems().size() == MIN_TOPPINGS){
            // ALERT too few toppings but allow removal of toppings
        }

        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();

        if(selectedTopping == null){
            return;
        }

        selectedToppings.getItems().remove(selectedTopping);
        toppingsList.getItems().add(selectedTopping);
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        price.clear();
        price.appendText("" + pizza.price());
    }
}
