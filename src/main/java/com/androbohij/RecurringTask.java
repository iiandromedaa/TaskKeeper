package com.androbohij;
import java.util.Date;

public class RecurringTask extends Task {

    /**
     * could have been an enum but the assignment said string, so string it is
     * TODO maybe replace with enum after assignment
     */
    private String recurrencePattern;

    public RecurringTask(String name, String description, Date dueDate, boolean taskStatus, String recurrencePattern) {
        super(name, description, dueDate, taskStatus);
        this.recurrencePattern = recurrencePattern;
    }

    /**
     * @return the pattern of this tasks recurrence e.g: "DAILY", "WEEKLY"
     */
    public String getRecurrence() {
        return recurrencePattern;
    }

}
