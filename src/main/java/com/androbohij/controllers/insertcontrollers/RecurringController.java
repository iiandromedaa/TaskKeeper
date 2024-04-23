package com.androbohij.controllers.insertcontrollers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

public class RecurringController extends InsertController {

    @FXML
    private AnchorPane anchorPane;

    //TODO replace with recurrence pattern enum
    @FXML
    private ComboBox<String> recurrence;

	@Override
	public void bindings() {
		String list[] = {"Daily", "Weekly", "Monthly", "Yearly"};
		recurrence.setItems(FXCollections.observableArrayList(list));
		recurrence.valueProperty().addListener((obs, oldValue, newValue) -> getParent().setRecurrencePattern(newValue));
        if (getParent().getRecurrencePattern() != null) {
            for (String string : list) {
                if (getParent().getRecurrencePattern().equals(string)) {
                    recurrence.setValue(string);
                }
            }
        }
	}

}