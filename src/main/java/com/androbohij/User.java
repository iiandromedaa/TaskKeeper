package com.androbohij;

import java.util.ArrayList;

public class User {
    
    private TodoList todoList;

    public User() {
        todoList = new TodoList();
    }

    /**
     * @return todolist associated with user object
     */
    public TodoList getTodoList() {
        return todoList;
    }

    /**
     * for injecting a todolist into the user object when loading from save
     * @param todoList to be loaded
     */
    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    @Deprecated
    /**
     * since im using javafx now i dont really need this
     * but its a silly relic of when this was in the command line
     */
    public void displayTodoList() {
        ArrayList<Task> list = todoList.displayTasks();
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.print("[" + i + "] ");
            if (task instanceof RecurringTask) {
                //yippee explicit casts
                System.out.print("Task type: Recurring, ");
                System.out.print("Task recurrence: " + ((RecurringTask)task).getRecurrence());
            } else if (task instanceof RegularTask) {
                System.out.print("Task type: Regular, ");
                System.out.print("Task priority: " + ((RegularTask)task).getPriority());
            } else if (task instanceof ImportantTask) {
                System.out.print("Task type: Important, ");
                System.out.print("Task is urgent?: " + ((ImportantTask)task).getUrgency());
            }
            System.out.print(", Task name: " + task.getName());
            System.out.print(", Task description: " + task.getDescription());
            System.out.print(", Task due date: " + task.getDueDate().toString());
            System.out.println(", Task completion: " + task.getTaskStatus());
            System.out.println("task card x: " + task.getX() + " task card y: " + task.getY());
        }
    }

}
