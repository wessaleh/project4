package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StoreOrderController {

    private MainController mainController;

    /**
     * Connects this controller to main controller
     * @param controller - the main controller
     */
    public void setMainController(MainController controller){
        this.mainController = controller;
    }
}
