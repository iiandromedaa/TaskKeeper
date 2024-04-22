package com.androbohij.controllers.insertcontrollers;

import com.androbohij.controllers.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;

public class RegularController extends InsertController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Spinner<Integer> priority;

    @Override
    public void initialize() {
        priority.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 3, 1));
        priority.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                try {
                    priority.increment(0);
                    getParent().setPriority(priority.getValue());
                } catch (NumberFormatException e) {
                    priority.getEditor().textProperty().set("1");
                    getParent().setPriority(priority.getValue());
                }
            }
          });
    }



}