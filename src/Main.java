import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanscan = new Scanner(System.in);
        User user = new User();
        CLUtility.clear();
        System.out.println("welcome to the task manager, not to be confused with Task Manager");
        switch(CLUtility.switchValidator("select a thing to do \n1) add task\n2) delete task\n3) display task list\n4) exit", 0, scanscan)) {
            case 1:
                String name, desc;
                Date dueDate;
                boolean taskStatus;
                break;
            case 2:
                break;
            case 3: 
                break;
            case 4:
                System.out.println("ok");
                System.exit(0);
            default:
                System.out.println("how??");
                System.out.println("no more program for you");
                System.exit(0);
        }
        user.getTodoList().addTask(new ImportantTask("name", "desc", new Date(), false, true));
        user.displayTodoList();


    }
    
}
