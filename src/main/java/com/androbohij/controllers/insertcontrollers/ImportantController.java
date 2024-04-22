package com.androbohij.controllers.insertcontrollers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

public class ImportantController extends InsertController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private CheckBox urgency;

	@Override
	public void initialize() {
        urgency.setOnAction(event -> {
            getParent().setIsUrgent(urgency.isSelected());
        });
    }

}
