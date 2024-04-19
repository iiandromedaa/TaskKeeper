package com.androbohij;

import java.io.IOException;
import java.util.Date;

import com.androbohij.Task.TaskTypes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class MainController extends Controller {

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
    }

    @FXML
    void addTaskDialog(ActionEvent event) {
        try {
            // App.makePopUp("Add task", super.getStage());
            createTask(TaskTypes.RECURRING, "gdfg", "gdfgff", new Date(), false, "daily", 0);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("i didnt implement the add task dialog yet");
        }
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
        
        // Scene scene = new Scene(par);
        canvas.getChildren().add(par);
        par.relocate(100, 100);
        par.addEventHandler (MouseEvent.MOUSE_RELEASED, ev -> par.toFront ());
        App.getUser().getTodoList().addTask(task);
        ((TaskController)fxmlLoader.getController()).setTask(task);
        
    }

}
