package com.androbohij.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;

import com.androbohij.App;
import com.androbohij.ImportantTask;
import com.androbohij.RecurringTask;
import com.androbohij.RegularTask;
import com.androbohij.Task;
import com.androbohij.Task.TaskTypes;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class AddTaskController extends Controller {

    private AnchorPane canvas;
    private int priority;
    private boolean isUrgent;
    private String recurrencePattern;
    private LocalDate datePicked;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label denier;

    @FXML
    private Button okButton;

    @FXML
    private TextArea taskDescArea;

    @FXML
    private TextField taskNameField;

    @FXML
    private AnchorPane taskQualityPane;

    @FXML
    private ComboBox<TaskTypes> taskTypeBox;

    @FXML
    void cancel(ActionEvent event) {
        getStage().close();
    }

    @FXML
    void validate(ActionEvent event) throws IOException {
        System.out.println(datePicker.getValue());
        if ((taskNameField.getText().isBlank() || datePicker.getEditor().getText().isBlank() 
        || datePicker.getValue() == null || taskTypeBox.getSelectionModel().isEmpty())
        || (taskTypeBox.getSelectionModel().getSelectedItem().equals(TaskTypes.RECURRING) && recurrencePattern == null)) {
            denier.setText("Missing required fields!");
            return;
        } else {
            Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            createTask(taskTypeBox.getSelectionModel().getSelectedItem(), taskNameField.getText(), taskDescArea.getText(), date, isUrgent, recurrencePattern, priority);
            cancel(event);
        }
    }

    public void initialize() {
        TaskTypes list[] = {TaskTypes.REGULAR, TaskTypes.RECURRING, TaskTypes.IMPORTANT};
        taskTypeBox.setItems(FXCollections.observableArrayList(list));

        taskTypeBox.setOnAction(event -> {
            TaskTypes taskTypes = taskTypeBox.getSelectionModel().getSelectedItem();
            if (taskTypes.equals(TaskTypes.REGULAR)) {
                System.out.println("reg");
            } else if (taskTypes.equals(TaskTypes.RECURRING)) {
                System.out.println("rec");
            } else if (taskTypes.equals(TaskTypes.IMPORTANT)) {
                System.out.println("imp");
            }
        });

        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate minDate = LocalDate.now();
                setDisable(date.isBefore(minDate));
            }
        });

        fixDatePicker();
    }

    public void bindings(AnchorPane ap) {
        canvas = ap;
    }

    private void update() {

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

    /**
     * as of JavaFX 21, DatePicker throws an exception when given an invalid date so this is a fix for that
     */
    public void fixDatePicker() {
        StringConverter<LocalDate> stringConverter = new StringConverter<LocalDate>() {
            
            @Override
            public LocalDate fromString(String value) {
                try {
                   return LocalDate.parse(value);
                } catch (Exception e) {
                    datePicker.getEditor().clear();
                    return null;
                }
            }

			@Override
			public String toString(LocalDate object) {
                if (object == null)
                    return "";
				return object.toString();
			}
        };
        datePicker.setConverter(stringConverter);
    }

}

