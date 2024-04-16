import java.util.ArrayList;

public class User {
    
    private TodoList todoList;

    public User() {
        todoList = new TodoList();
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void displayTodoList() {
        ArrayList<Task> list = todoList.displayTasks();
        for (Task task : list) {
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
        }
    }

}
