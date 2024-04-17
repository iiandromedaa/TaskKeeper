package com.androbohij;
import java.util.ArrayList;

public class TodoList {

    private ArrayList<Task> list;

    public TodoList() {
        list = new ArrayList<Task>();
    }
 
    /**
     * @param task adds a task, simple as
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * @param i index of the task you wanna nuke
     */
    public void deleteTask(int i) {
        list.remove(i);
    }

    //hands off tasklist to be printed by user class, i feel like 
    //it should handle printing since its more front facing
    /**
     * @return this todolist's arraylist containing various tasks
     */
    public ArrayList<Task> displayTasks() {
        return list;
    }
    
}
