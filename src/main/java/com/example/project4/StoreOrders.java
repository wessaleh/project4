package com.example.project4;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Store Orders class for keeping track of orders and exporting them
 * @author Wesam Saleh, Najibullah Assadullah
 */

public class StoreOrders {
    protected ArrayList<Order> orders;

    /**
     * Constructor for store orders class
     */
    public StoreOrders(){
        this.orders = new ArrayList<>(); }

    /**
     * exports the store orders to a txt file
     */
    public void export() throws FileNotFoundException {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage);
        PrintWriter printWriter = new PrintWriter(targetFile);
        for (int i = 0; i < orders.size(); i++){
            printWriter.println("Phone Number: " + orders.get(i).phoneNumber);
            printWriter.println("Pizzas:");
            for (int j = 0; j < orders.get(i).pizzas.size(); j++){
                printWriter.println(orders.get(i).pizzas.get(j).toString());
            }
            printWriter.println("Total: " + orders.get(i).orderTotal);
            printWriter.println();
        }
        printWriter.close();
    }
}
