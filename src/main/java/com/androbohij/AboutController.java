package com.androbohij;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AboutController extends Controller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button closeAbout;

    @FXML
    void close(ActionEvent event) {
        getStage().close();
    }

}
