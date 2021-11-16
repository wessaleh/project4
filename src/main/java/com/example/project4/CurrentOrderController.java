package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;

/**
 * Controller for Current Order GUI
 * This would be the cart for the pizza order
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class CurrentOrderController {

    private final double TAX_RATE = 0.06625;
    private final double TOTAL_RATE = 1 + TAX_RATE;
    private static final int NUM_DECIMAL_PLACES = 2;
    private static final int NUM_INT_PLACES = 1;
    private DecimalFormat money_Format;

    private MainController mainController;
    private Alert error_message;
    private Stage stage;

    @FXML
    private ListView<Pizza> cart;

    @FXML
    private TextField orderTotal;

    @FXML
    private TextField phoneNumber;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField subtotal;

    /**
     * Sets the values of the order prices
     * @param stage - the stage to set
     */
    protected void setValues(Stage stage) {
        this.stage = stage;

        // setting money_format to print to two decimal places
        money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(NUM_DECIMAL_PLACES);
        money_Format.setMinimumIntegerDigits(NUM_INT_PLACES);

        double subTotal = mainController.getSubTotal();

        // loading the new values for the totals
        subtotal.clear();
        subtotal.appendText("" + money_Format.format(subTotal));
        salesTax.clear();
        salesTax.appendText("" + money_Format.format(subTotal * TAX_RATE));
        orderTotal.clear();
        orderTotal.appendText("" + money_Format.format(subTotal * TOTAL_RATE));
    }

    /**
     * Adds current order to store orders
     */
    @FXML
    void placeOrder() {
        // Nothing added to order
        if(cart.getItems().size() == 0){
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please add a pizza to the cart first.");
            error_message.showAndWait();
        }

        // creating the new order
        Order orderToAdd = new Order();
        orderToAdd.phoneNumber = phoneNumber.getText();
        orderToAdd.pizzas = FXCollections.observableArrayList(mainController.getCurrentOrderPizzas());
        orderToAdd.orderTotal = Double.parseDouble(orderTotal.getText());
        // adding to store orders
        mainController.addOrderToStoreOrders(orderToAdd);

        // clearing the current order
        mainController.clearOrder();
        stage.close();
    }

    /**
     * Removes a pizza from the cart
     */
    @FXML
    void removePizza() {
        // No pizza to remove
        if(cart.getItems().size() == 0){
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please add a pizza to the cart first.");
            error_message.showAndWait();
            return;
        }

        Pizza pizzaToRemove = cart.getSelectionModel().getSelectedItem();

        // No pizza selected to remove
        if(pizzaToRemove == null){
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please select a pizza from the cart to remove.");
            error_message.showAndWait();
            return;
        }

        // removing the selected pizza
        mainController.removePizzaFromOrder(pizzaToRemove);
        cart.getItems().remove(pizzaToRemove);
        this.setValues(this.stage);
    }

    /**
     * Connects this controller to main controller
     * @param controller - the main controller
     */
    public void setMainController(MainController controller){
        this.mainController = controller;
    }

    /**
     * Sets the phone number text field
     * @param text - text to set
     */
    public void setPhoneNumberText(String text) {
        phoneNumber.appendText(text);
    }

    /**
     * Sets the items in cart
     * @param pizzasInCart - pizzas to populate the listview
     */
    public void setItemsInCart(ObservableList<Pizza> pizzasInCart){
        cart.setItems(pizzasInCart);
    }
}
