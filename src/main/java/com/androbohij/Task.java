package com.androbohij;
import java.io.Serializable;
import java.util.Date;

//felt like trying out abstract classes since we're doing class relationships
//also serialization to save your tasks
public abstract class Task implements Serializable {

    private String name;
    private String description;
    private Date dueDate;
    private boolean taskStatus;
    /**
     * exists only to store task card position for serialization
     */
    private int x;
    /**
     * exists only to store task card position for serialization
     */
    private int y;

    protected Task(String name, String description, Date dueDate, boolean taskStatus) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.taskStatus = taskStatus;
    }

    /**
     * @return x position of task card
     */
    protected int getX() {
        return x;
    }

    /**
     * @return y position of task card
     */
    protected int getY() {
        return y;
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

    public TaskTypes getTaskType() {
        if (this instanceof ImportantTask)
            return TaskTypes.IMPORTANT;
        else if (this instanceof RecurringTask)
            return TaskTypes.RECURRING;
        else
            return TaskTypes.REGULAR;
    }

    /**
     * enum to make my life easier when telling which task is which
     * <p>
     * i could have nested this in user since it handled checking task type before,
     * or i could have made it its own file, or even just had this as a method that returns a string
     * but i wanted to try out enums
     */
    public static enum TaskTypes {

        IMPORTANT("Important"),
        RECURRING("Recurring"),
        REGULAR("Regular");

        private final String type;

        private TaskTypes(String type) {
            this.type = type;
        }

        public String getTypeAsString() {
            return type;
        }
    }

    /**
     * @param x x position to set task card to
     */
    protected void setX(int x) {
        this.x = x;
    }

    /**
     * @param y y position to set task card to
     */
    protected void setY(int y) {
        this.x = y;
    }

}
