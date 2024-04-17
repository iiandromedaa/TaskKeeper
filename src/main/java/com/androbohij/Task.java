package com.androbohij;
import java.util.Date;

//felt like trying out abstract classes since we're doing class relationships
public abstract class Task {

    private String name;
    private String description;
    private Date dueDate;
    private boolean taskStatus;

    protected Task(String name, String description, Date dueDate, boolean taskStatus) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
    }

    /**
     * @return returns task's name
     */
    protected String getName() {
        return name;
    }

    /**
     * @return returns task's description
     */
    protected String getDescription() {
        return description;
    }

    /**
     * @return returns task's due date
     */
    protected Date getDueDate() {
        return dueDate;
    }

    /**
     * @return returns task's completion status
     */
    protected boolean getTaskStatus() {
        return taskStatus;
    }


}
