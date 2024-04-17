package com.androbohij;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;

public class mainController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private AnchorPane canvas;

    @FXML
    private Menu menuAdd;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuHelp;

    @FXML
    private MenuItem menuHelpAbout;

    @FXML
    private MenuItem menuHelpClose;

    @FXML
    private Menu menuView;

    @FXML
    private MenuItem menuViewTaskList;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label versionLabel;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        versionLabel.setText("JavaFX " + javafxVersion + " Java " + javaVersion);

        canvas.setPrefSize(Screen.getPrimary().getBounds().getMaxX(), Screen.getPrimary().getBounds().getMaxY());

        canvas.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() != MouseButton.MIDDLE) event.consume();
        });
    }

    @FXML
    void addTaskDialog(ActionEvent event) {

    }

    @FXML
    void closeApp(ActionEvent event) {
        App.save();
        Platform.exit();
    }

    @FXML
    void openAbout(ActionEvent event) {

    }

    @FXML
    void openTaskList(ActionEvent event) {

    }

}
