package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static com.example.project4.PizzaMaker.createPizza;

/**
 * Controller for GUI that adds pizza to order
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class PizzaController{

    private final int MAX_TOPPINGS = 7;

    private MainController mainController;
    private int MIN_TOPPINGS;
    private Pizza pizza;
    private Alert popup;
    private ObservableList<Topping> DEFAULT_TOPPINGS = FXCollections.observableArrayList();

    private static final int NUM_DECIMAL_PLACES = 2;
    private static final int NUM_INT_PLACES = 1;
    private DecimalFormat money_Format;

    @FXML
    private ImageView pizzaImage;

    @FXML
    private Button pizzaLabel;

    @FXML
    private ComboBox<Size> pizzaSize;

    @FXML
    private TextField price;

    @FXML
    private ListView<Topping> selectedToppings;

    @FXML
    private ListView<Topping> toppingsList;

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
        // populating the listviews for default toppings
        ObservableList<Size> pizzaSizes = FXCollections.observableArrayList(Size.values());
        ObservableList<Topping> toppings = FXCollections.observableArrayList(Topping.values());
        toppingsList.setItems(toppings);
        pizzaSize.setItems(pizzaSizes);

        // creating the for current order
        pizza = createPizza("Deluxe");

        // setting formatter to print to two decimal places
        money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(NUM_DECIMAL_PLACES);
        money_Format.setMinimumIntegerDigits(NUM_INT_PLACES);
    }

    /**
     * Sets the default values of the pizza order stage
     * @param img - the image to set
     * @param pizzaType - the type of pizza
     * @param minToppings - the minimum number of toppings
     * @param toppings - the default toppings for this pizza
     */
    public void setDefaultValues(Image img, String pizzaType, int minToppings, ObservableList<Topping> toppings) {
        DEFAULT_TOPPINGS = toppings;

        // Setting the default list view values
        toppingsList.getItems().removeAll(DEFAULT_TOPPINGS);
        selectedToppings.getItems().addAll(DEFAULT_TOPPINGS);

        // Setting the pizza image, label, and minimum number of toppings
        pizzaImage.setImage(img);
        pizzaLabel.setText(pizzaType);
        MIN_TOPPINGS = minToppings;

        // creating the pizza
        pizza = createPizza(pizzaType);

        // setting default value for the pizza
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        pizza.size = Size.Small;
        pizzaSize.getSelectionModel().selectFirst();
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }

    /**
     * Adds pizza to the current order
     */
    @FXML
    void addPizzaToOrder() {
        // adding pizza to current order
        Pizza pizzaToAdd = pizza.copy();
        mainController.addPizzaToCurrentOrder(pizzaToAdd);

        // alerting the user that the pizza has been added
        popup = new Alert(Alert.AlertType.INFORMATION);
        popup.setTitle("Order");
        popup.setContentText("Pizza added to the order!");
        popup.showAndWait();
    }

    /**
     * Adds a topping on the pizza
     */
    @FXML
    void addTopping() {
        // Exceeding the maximum toppings number
        if(selectedToppings.getItems().size() == MAX_TOPPINGS){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Adding toppings");
            popup.setContentText("Cannot add more toppings. Maximum number of toppings is 7!");
            popup.showAndWait();
            return;
        }

        Topping selectedTopping = toppingsList.getSelectionModel().getSelectedItem();

        // no topping selected to add
        if(selectedTopping == null){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Adding toppings");
            popup.setContentText("Please select a topping to add!");
            popup.showAndWait();
            return;
        }

        // removing the topping form the additional toppings list view
        toppingsList.getItems().remove(selectedTopping);
        // adding the topping to the selected toppings list view
        selectedToppings.getItems().add(selectedTopping);
        // updating the pizza toppings
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        // updating the price
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }

    /**
     * Removes a topping from the pizza
     */
    @FXML
    void removeTopping() {
        // Trying to remove the essential toppings
        if(DEFAULT_TOPPINGS.contains(selectedToppings.getSelectionModel().getSelectedItem())){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Removing toppings");
            popup.setContentText("Cannot remove this essential ingredient.");
            popup.showAndWait();
            return;
        }

        Topping selectedTopping = selectedToppings.getSelectionModel().getSelectedItem();

        // No topping selected to remove
        if(selectedTopping == null){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Removing toppings");
            popup.setContentText("Please select a topping to remove!");
            popup.showAndWait();
            return;
        }

        // removes the topping from selected toppings list view
        selectedToppings.getItems().remove(selectedTopping);
        // adds the topping back to the additional toppings list view
        toppingsList.getItems().add(selectedTopping);
        // updating the pizza toppings
        pizza.toppings = new ArrayList(selectedToppings.getItems());
        // updating the price
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }

    /**
     * Changes the size of the pizza on selection
     */
    @FXML
    void changePizzaSize() {
        // updates the price to account for pizza size
        pizza.size = pizzaSize.getValue();
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }
}
