package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;

/**
 * Controller for Current Order GUI
 * This would be the cart for the pizza order
 * @author Wesam Saleh
 */

public class CurrentOrderController {

    private final double TAX_RATE = 0.06625;
    private final double TOTAL_RATE = 1 + TAX_RATE;
    private static final int NUM_DECIMAL_PLACES = 2;
    private static final int NUM_INT_PLACES = 1;
    private DecimalFormat money_Format;

    private MainController mainController;
    private Alert error_message;

    @FXML
    protected ListView<Pizza> cart;

    @FXML
    protected TextField orderTotal;

    @FXML
    protected TextField phoneNumber;

    @FXML
    public TextField salesTax;

    @FXML
    public TextField subtotal;

    /**
     * Sets the values of the order prices
     */
    protected void setValues() {
        money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(NUM_DECIMAL_PLACES);
        money_Format.setMinimumIntegerDigits(NUM_INT_PLACES);

        double subTotal = mainController.getSubTotal();

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
        if(cart.getItems().size() == 0){
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please add a pizza to the cart first.");
            error_message.showAndWait();
        }

        mainController.currentOrder.phoneNumber = phoneNumber.getText();
        mainController.currentOrder.orderTotal = Double.parseDouble(orderTotal.getText());

        mainController.storeOrders.orders.add(mainController.currentOrder);
    }

    /**
     * Removes a pizza from the cart
     */
    @FXML
    void removePizza() {
        if(cart.getItems().size() == 0){
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please add a pizza to the cart first.");
            error_message.showAndWait();
            return;
        }

        Pizza pizzaToRemove = cart.getSelectionModel().getSelectedItem();

        if(pizzaToRemove == null){
            error_message = new Alert(Alert.AlertType.ERROR);
            error_message.setContentText("Please select a pizza from the cart to remove.");
            error_message.showAndWait();
            return;
        }

        mainController.currentOrder.pizzas.remove(pizzaToRemove);
        cart.getItems().remove(pizzaToRemove);
        this.setValues();
    }

    /**
     * Connects this controller to main controller
     * @param controller - the main controller
     */
    public void setMainController(MainController controller){
        this.mainController = controller;
    }


}
