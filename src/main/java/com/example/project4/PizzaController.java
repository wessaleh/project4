package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private Alert error_message;
    protected ObservableList<Topping> DEFAULT_TOPPINGS = FXCollections.observableArrayList();

    private static final int NUM_DECIMAL_PLACES = 2;
    private static final int NUM_INT_PLACES = 1;
    private DecimalFormat money_Format;

    @FXML
    protected ImageView pizzaImage;

    @FXML
    protected Button pizzaLabel;

    @FXML
    protected ComboBox<Size> pizzaSize;

    @FXML
    protected TextField price;

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

        pizza = createPizza("Deluxe");
        money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(NUM_DECIMAL_PLACES);
        money_Format.setMinimumIntegerDigits(NUM_INT_PLACES);
    }

    /**
     * Sets the default values of the pizza order stage
     */
    public void setDefaultValues() {
        toppingsList.getItems().removeAll(DEFAULT_TOPPINGS);
        selectedToppings.getItems().addAll(DEFAULT_TOPPINGS);
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        pizza.size = Size.Small;
        pizzaSize.getSelectionModel().selectFirst();
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }

    @FXML
    void addPizzaToOrder(ActionEvent event) {
        // Create the pizza
        // add to main controller's pizza list
    }

    /**
     * Adds a topping on the pizza
     * @param event - the event to handle
     */
    @FXML
    void addTopping(ActionEvent event) {
        if(selectedToppings.getItems().size() == MAX_TOPPINGS){
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Cannot add more toppings.");
            error_message.showAndWait();
        }

        Topping selectedTopping = toppingsList.getSelectionModel().getSelectedItem();

        if(selectedTopping == null){
            return;
        }

        toppingsList.getItems().remove(selectedTopping);
        selectedToppings.getItems().add(selectedTopping);
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }

    /**
     * Removes a topping from the pizza
     * @param event - the event to handle
     */
    @FXML
    void removeTopping(ActionEvent event) {
        if(DEFAULT_TOPPINGS.contains(selectedToppings.getSelectionModel().getSelectedItem())){
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Cannot remove this essential ingredient.");
            error_message.showAndWait();
            return;
        }

        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();

        if(selectedTopping == null){
            return;
        }

        selectedToppings.getItems().remove(selectedTopping);
        toppingsList.getItems().add(selectedTopping);
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }

    /**
     * Changes the size of the pizza on selection
     * @param event - the event to handle
     */
    @FXML
    void changePizzaSize(ActionEvent event) {
        pizza.size = pizzaSize.getValue();
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }
}
