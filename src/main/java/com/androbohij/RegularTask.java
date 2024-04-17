package com.androbohij;
import java.util.Date;

public class RegularTask extends Task {

    private int priority;

    public RegularTask(String name, String description, Date dueDate, boolean taskStatus, int priority) {
        super(name, description, dueDate, taskStatus);
        this.priority = priority;
    }

    /**
     * @return priority of the task
     */
    public int getPriority() {
        return priority;
    }
    
}
