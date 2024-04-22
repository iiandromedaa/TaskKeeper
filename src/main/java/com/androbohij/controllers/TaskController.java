package com.androbohij.controllers;

import java.text.DateFormat;
import java.util.Locale;

import com.androbohij.App;
import com.androbohij.ImportantTask;
import com.androbohij.RecurringTask;
import com.androbohij.RegularTask;
import com.androbohij.Task;
import com.androbohij.Task.TaskTypes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TaskController extends Controller {

    private Task task;
    private TaskTypes type;
    private double x, y;
    private double newX, newY;
    private AnchorPane canvas;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button closeButton;

    @FXML
    private Label taskCardHandle;

    @FXML
    private CheckBox taskCheck;

    @FXML
    private HBox taskContent;

    @FXML
    private Pane taskContentPane;

    @FXML
    private Label taskDescription;

    @FXML
    private Label taskDueDate;

    @FXML
    private Label taskQuality;

    @FXML
    private Pane taskSideBar;

    @FXML
    private Label taskType;

    @FXML
    private TitledPane titleBar;

    @FXML
    void removeTask(ActionEvent event) {
        App.getUser().getTodoList().removeTask(task);
        canvas.getChildren().remove(anchorPane);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
        type = task.getTaskType();
        taskCardHandle.setText(task.getName());
        taskDescription.setText(task.getDescription());
        taskType.setText(type.toString());
        taskDueDate.setText("Due on " + DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(task.getDueDate()));
        if (type.equals(TaskTypes.IMPORTANT)) {
            taskQuality.setText("Urgent: " + ((ImportantTask)task).getUrgency());
            taskSideBar.getStyleClass().add("important");
        } else if (type.equals(TaskTypes.RECURRING)) {
            taskQuality.setText("Repeats " + ((RecurringTask)task).getRecurrence() + ", due again on ");
            taskSideBar.getStyleClass().add("recurring");
        } else {
            taskQuality.setText("Priority: " + ((RegularTask)task).getPriority());
            taskSideBar.getStyleClass().add("regular");
        } 

        drag(taskCardHandle);

        titleBar.addEventHandler(MouseEvent.ANY, event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                event.consume();
            }
        });

        taskCardHandle.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                if (titleBar.isExpanded())
                    titleBar.setExpanded(false);
                else
                    titleBar.setExpanded(true);
            }
        }); 

        taskCheck.setOnAction(event -> {
            if (taskCheck.isSelected())
                taskCardHandle.getStyleClass().add(".strikethrough .text");
            else if (!taskCheck.isSelected())
                taskCardHandle.getStyleClass().remove(0);
        });
    }

    public void initialize() {
        Tooltip.install(taskCardHandle, new Tooltip("Task title, Left click to drag, Right click to collapse/expand"));
        Tooltip.install(taskDescription, new Tooltip("Task description"));
        Tooltip.install(taskType, new Tooltip("Task type"));
        Tooltip.install(taskCheck, new Tooltip("Task completion"));
    }

    public void bindings(AnchorPane ap) {
        canvas = ap;
    }

    private void drag(Node handle) {
        handle.setOnMousePressed(event -> {
            //hard coding these offets cause they seem to work
            x = event.getX() + 26;
            y = event.getY() + 26;
        });
    
        handle.setOnMouseDragged(event -> {
            newX = event.getSceneX() - x - MainController.getBounds().getMinX();
            newY = event.getSceneY() - y - MainController.getBounds().getMinY();

            if (newX > getStage().getMaxWidth() - anchorPane.getWidth()) {
                //prevent going right of bounds
                anchorPane.setLayoutX(getStage().getMaxWidth() - anchorPane.getWidth());
                task.setX(getStage().getMaxWidth() - anchorPane.getWidth());
            } else if (newX < 0) {
                //prevent going left of bounds
                anchorPane.setLayoutX(0);
                task.setX(0);
            } else {
                //normal move
                anchorPane.setLayoutX(newX);
                task.setX(newX);
            }
            
            if (newY > getStage().getMaxHeight() - anchorPane.getHeight()) {
                //prevent going below bounds
                anchorPane.setLayoutY(getStage().getMaxHeight() - anchorPane.getHeight());
                task.setY(getStage().getMaxHeight() - anchorPane.getHeight());
            } else if (newY < 0) {
                //prevent going above bounds
                anchorPane.setLayoutY(0);
                task.setY(0);
            } else {
                //normal move
                anchorPane.setLayoutY(newY);
                task.setY(newY);
            }
        });
    }

}