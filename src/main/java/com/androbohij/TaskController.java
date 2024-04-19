package com.androbohij;

import java.text.DateFormat;
import java.util.Locale;

import com.androbohij.Task.TaskTypes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TaskController extends Controller {

    private Task task;
    private TaskTypes type;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button closeButton;

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
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
        type = task.getTaskType();
        titleBar.setText(task.getName());
        taskDescription.setText(task.getDescription());
        taskType.setText(type.getTypeAsString());
        taskDueDate.setText("Due on " + DateFormat.getDateInstance(DateFormat.LONG, Locale.US).format(task.getDueDate()));
        if (type.equals(TaskTypes.IMPORTANT)) {
            taskQuality.setText("Urgent: " + ((ImportantTask)task).getUrgency());
        } else if (type.equals(TaskTypes.RECURRING)) {
            taskQuality.setText("Repeats " + ((RecurringTask)task).getRecurrence() + ", due again on ");
        } else {
            taskQuality.setText("Priority: " + ((RegularTask)task).getPriority());
        } 
    }

    public void initialize() {
        
    }

}
