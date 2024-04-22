package com.androbohij.controllers;

import java.io.IOException;
import java.util.Date;

import com.androbohij.App;
import com.androbohij.ImportantTask;
import com.androbohij.RecurringTask;
import com.androbohij.RegularTask;
import com.androbohij.Task;
import com.androbohij.Task.TaskTypes;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController extends Controller {

    private static Bounds viewportBounds;

    @FXML
    private MenuItem addNewTask;

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

        InvalidationListener l = o -> update();
        scrollPane.viewportBoundsProperty().addListener(l);
    }

    private void update() {
        viewportBounds = scrollPane.getViewportBounds();
    }

    //fuck it we static, theres 
    public static Bounds getBounds() {
        return viewportBounds;
    }

    @FXML
    void addTaskDialog(ActionEvent event) throws IOException {
        Stage popUp = new Stage();
        popUp.setTitle("Add task");
        popUp.initModality(Modality.WINDOW_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addtask.fxml"));
        Parent par = fxmlLoader.load();
        ((Controller)fxmlLoader.getController()).setStage(popUp);
        ((AddTaskController)fxmlLoader.getController()).bindings(canvas);
        Scene scene = new Scene(par);
        popUp.initOwner(getStage());
        popUp.setScene(scene);
        popUp.setResizable(false);
        popUp.show();
    }

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void openAbout(ActionEvent event) {
        try {
            App.makePopUp("About");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openTaskList(ActionEvent event) {

    }

    private void createTask(TaskTypes type, String name, String desc, 
    Date dueDate, boolean urgency, String recurrence, int priority) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("task.fxml"));
        AnchorPane par = fxmlLoader.load();
        Task task;

        if (type.equals(TaskTypes.IMPORTANT)) {
            task = new ImportantTask(name, desc, dueDate, false, urgency);
        } else if (type.equals(TaskTypes.RECURRING)) {
            task = new RecurringTask(name, desc, dueDate, false, recurrence);
        } else {
            task = new RegularTask(name, desc, dueDate, false, priority);
        } 
        
        canvas.getChildren().add(par);
        par.addEventHandler (MouseEvent.MOUSE_RELEASED, ev -> par.toFront ());
        App.getUser().getTodoList().addTask(task);
        ((TaskController)fxmlLoader.getController()).setStage(getStage());
        ((TaskController)fxmlLoader.getController()).bindings(canvas);
        ((TaskController)fxmlLoader.getController()).setTask(task);
    }

}