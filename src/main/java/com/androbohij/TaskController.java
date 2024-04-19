package com.androbohij;

public class TaskController extends Controller {
    
    private Task task;
    private String type;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void initialize() {
        type = task.getTaskType(task).getTypeAsString();
    }

}
