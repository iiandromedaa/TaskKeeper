package com.androbohij.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.androbohij.App;
import com.androbohij.ImportantTask;
import com.androbohij.RecurringTask;
import com.androbohij.RegularTask;
import com.androbohij.Task;
import com.androbohij.Task.TaskTypes;
import com.androbohij.controllers.insertcontrollers.InsertController;

import javafx.collections.FXCollections;
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
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class AddTaskController extends Controller {

    private AnchorPane canvas;
    /**
     * default of 1, because the priority spinner starts on 1 and doesn't
     * send values if it isnt touched at all, so previously this could lead
     * to regular tasks having a priority of 0 which is not supposed to be possible
     */
    private int priority = 1;
    private boolean isUrgent;
    private String recurrencePattern;

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
        if ((taskNameField.getText().isBlank() || datePicker.getEditor().getText().isBlank()
        || datePicker.getValue() == null || taskTypeBox.getSelectionModel().isEmpty())
        || (taskTypeBox.getSelectionModel().getSelectedItem().equals(TaskTypes.RECURRING)
        && recurrencePattern == null)) {
            denier.setText("Missing required fields!");
            return;
        } else {
            LocalDate date = datePicker.getValue();
            createTask(taskTypeBox.getSelectionModel().getSelectedItem(), taskNameField.getText(), 
                       taskDescArea.getText(), date, isUrgent, recurrencePattern, priority);
            cancel(event);
        }
    }

    public void initialize() {
        TaskTypes list[] = {TaskTypes.REGULAR, TaskTypes.RECURRING, TaskTypes.IMPORTANT};
        taskTypeBox.setItems(FXCollections.observableArrayList(list));

        taskTypeBox.setOnAction(event -> {
            TaskTypes taskTypes = taskTypeBox.getSelectionModel().getSelectedItem();
            if (taskTypes.equals(TaskTypes.REGULAR)) {
                try {
                    loadInsert("regular");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (taskTypes.equals(TaskTypes.RECURRING)) {
                try {
                    loadInsert("recurring");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (taskTypes.equals(TaskTypes.IMPORTANT)) {
                try {
                    loadInsert("important");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(LocalDate.now()));
            }
        });

        taskNameField.setTextFormatter(new TextFormatter<String>(change -> {
            if (change.getControlNewText().length() < 25)
                return change;
            else
                return null;
        }));

        taskDescArea.setTextFormatter(new TextFormatter<String>(change -> {
            //i settled on 200 char limit from guessing and checking what overflows the box
            if (change.getControlNewText().length() < 200)
                return change;
            else
                return null;
        }));

        fixDatePicker();
    }

    public void bindings(AnchorPane ap) {
        canvas = ap;
    }

    /**
     * makes tasks
     * @param type
     * @param name
     * @param desc
     * @param dueDate
     * @param urgency
     * @param recurrence
     * @param priority
     * @throws IOException
     */
    private void createTask(TaskTypes type, String name, String desc, 
    LocalDate dueDate, boolean urgency, String recurrence, int priority) throws IOException {
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
        par.addEventHandler(MouseEvent.ANY, e -> {
            if (e.getButton() == MouseButton.PRIMARY) par.toFront();
        });
        App.getUser().getTodoList().addTask(task);
        ((TaskController)fxmlLoader.getController()).setStage(getStage());
        ((TaskController)fxmlLoader.getController()).bindings(canvas);
        ((TaskController)fxmlLoader.getController()).setTask(task);
    }

    private void loadInsert(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("addtaskinsert/" + name + ".fxml"));
        AnchorPane par = fxmlLoader.load();
        ((InsertController)fxmlLoader.getController()).setParent(this);
        ((InsertController)fxmlLoader.getController()).bindings();
        taskQualityPane.getChildren().setAll(par);
    }

    /**
     * as of JavaFX 21, DatePicker throws an exception when given an invalid date so this is a fix for that
     */
    public void fixDatePicker() {
        StringConverter<LocalDate> stringConverter = new StringConverter<LocalDate>() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            @Override
            public LocalDate fromString(String value) {
                try {
                   return LocalDate.parse(value, formatter);
                } catch (Exception e) {
                    datePicker.getEditor().clear();
                    return null;
                }
            }

			@Override
			public String toString(LocalDate date) {
                if (date == null)
                    return "";
				return date.format(formatter);
			}
        };
        datePicker.setConverter(stringConverter);
    }

    public int getPriority() {
        return priority;
    }

    public boolean getIsUrgent() {
        return isUrgent;
    }

    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setIsUrgent(boolean isUrgent) {
        this.isUrgent = isUrgent;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }

}

