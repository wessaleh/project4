package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import static com.example.project4.PizzaMaker.createPizza;

/**
 * Controller for GUI that adds pizza to order
 * @author Wesam Saleh
 */

public class PizzaController{

    private final int MAX_TOPPINGS = 7;

    protected MainController mainController;
    protected int MIN_TOPPINGS;
    protected Pizza pizza;
    private Alert popup;
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

    /**
     * Adds pizza to the current order
     */
    @FXML
    void addPizzaToOrder() {
        mainController.currentOrder.pizzas.add(pizza);
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
        if(selectedToppings.getItems().size() == MAX_TOPPINGS){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Adding toppings");
            popup.setContentText("Cannot add more toppings. Maximum number of toppings is 7!");
            popup.showAndWait();
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
     */
    @FXML
    void removeTopping() {
        if(DEFAULT_TOPPINGS.contains(selectedToppings.getSelectionModel().getSelectedItem())){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setTitle("Removing toppings");
            popup.setContentText("Cannot remove this essential ingredient.");
            popup.showAndWait();
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
     */
    @FXML
    void changePizzaSize() {
        pizza.size = pizzaSize.getValue();
        price.clear();
        price.appendText("" + money_Format.format(pizza.price()));
    }
}
