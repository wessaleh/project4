package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Controller for viewing and exporting store orders GUI
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class StoreOrderController {

    private MainController mainController;

    @FXML
    private TextField orderTotal;

    @FXML
    protected ComboBox<String> phoneNumbers;

    @FXML
    protected ListView<Pizza> storeOrder;

    private Alert popup;
    private static final int NUM_DECIMAL_PLACES = 2;
    private static final int NUM_INT_PLACES = 1;
    private DecimalFormat money_Format;

    /**
     * Loads the order for a given phone number
     */
    @FXML
    void loadOrder() {
        int orderIndex = phoneNumbers.getSelectionModel().getSelectedIndex();

        if(orderIndex == -1){
            return;
        }

        // loads the selected store order list view
        storeOrder.getItems().clear();
        storeOrder.getItems().addAll(mainController.storeOrders.orders.get(orderIndex).pizzas);
        // loads the selected order total
        orderTotal.clear();
        orderTotal.appendText(money_Format.format(mainController.storeOrders.orders.get(orderIndex).orderTotal));
    }

    /**
     * Cancels the selected order
     */
    @FXML
    void cancelOrder() {
        int orderIndex = phoneNumbers.getSelectionModel().getSelectedIndex();

        // No order placed to remove
        if(orderIndex == -1){
            popup = new Alert(Alert.AlertType.ERROR);
            popup.setContentText("Please place an order first!");
            popup.showAndWait();
            return;
        }

        // clears the store order view
        storeOrder.getItems().clear();
        orderTotal.clear();
        phoneNumbers.getItems().remove(orderIndex);
        // removes the order from the store orders
        mainController.storeOrders.orders.remove(orderIndex);
    }

    /**
     * Exports store orders to a file chosen by the user
     */
    @FXML
    void exportStoreOrders() throws FileNotFoundException {
        mainController.storeOrders.export();
    }

    @FXML
    public void initialize() {
        // sets the formatter to two decimal places
        money_Format = new DecimalFormat("###,###.00");
        money_Format.setMinimumFractionDigits(NUM_DECIMAL_PLACES);
        money_Format.setMinimumIntegerDigits(NUM_INT_PLACES);
    }

    /**
     * Connects this controller to main controller
     * @param controller - the main controller
     */
    public void setMainController(MainController controller){
        this.mainController = controller;
    }
}
