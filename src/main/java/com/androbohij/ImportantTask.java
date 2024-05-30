package com.androbohij;
import java.time.LocalDate;

public class ImportantTask extends Task {

    private boolean isUrgent;

    public ImportantTask(String name, String description, LocalDate dueDate, boolean taskStatus, boolean isUrgent) {
        super(name, description, dueDate, taskStatus);
        this.isUrgent = isUrgent;
    }

    /**
     * @return if this task is urgent or not
     */
    public boolean getUrgency() {
        return isUrgent;
    }
    
}
