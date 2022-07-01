package com.example.clientgui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import static com.example.clientgui.HelloController.c;

public class EditQuantity {

    public static TempClass item;
    @FXML
    private Label itemNameLabel;
    @FXML
    private Spinner<Integer> mySpinner;

    @FXML
    public void initialize()
    {
        SpinnerValueFactory<Integer> valueFactory1 = //
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5, item.getQuantity());
        mySpinner.setValueFactory(valueFactory1);
        itemNameLabel.setText(item.getItemName());
    }

    @FXML
    void close(ActionEvent event) {
        c.getCart().editItem(item.itemName, mySpinner.getValue());
        Stage s = (Stage) ((Node) event.getSource()).getScene().getWindow();
        s.close();
    }
}
