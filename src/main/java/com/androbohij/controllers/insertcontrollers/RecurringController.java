package com.androbohij.controllers.insertcontrollers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class RecurringController extends InsertController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> recurrence;

	@Override
	public void initialize() {
		String list[] = {"Daily", "Weekly", "Monthly", "Yearly"};
		recurrence.setItems(FXCollections.observableArrayList(list));
		recurrence.valueProperty().addListener((obs, oldValue, newValue) -> getParent().setRecurrencePattern(newValue));
	}

}