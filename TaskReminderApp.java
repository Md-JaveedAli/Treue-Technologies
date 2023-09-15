import java.text.SimpleDateFormat;
import java.util.*;

class Task {
    private String title;
    private String description;
    private String category;
    private int priority;
    private Date dueDate;
    private boolean completed;

    public Task(String title, String description, String category, int priority, Date dueDate) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDueDate = dateFormat.format(dueDate);

        return "Title: " + title +
                "\nDescription: " + description +
                "\nCategory: " + category +
                "\nPriority: " + priority +
                "\nDue Date: " + formattedDueDate +
                "\nStatus: " + (completed ? "Completed" : "Not Completed");
    }
}

public class TaskReminderApp {
    private List<Task> tasks;
    private Scanner scanner;

    public TaskReminderApp() {
        tasks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addTask() {
        System.out.println("<<-------Enter TASK Details------->>");
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task category: ");
        String category = scanner.nextLine();
        System.out.print("Enter task priority (1 - 5): ");
        int priority = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter due date (yyyy-MM-dd): ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dueDate = null;
        try {
            dueDate = dateFormat.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Invalid date format. Task not added.");
            return;
        }

        Task task = new Task(title, description, category, priority, dueDate);
        tasks.add(task);

        System.out.println("*****Task added successfully*****");
        System.out.println("");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("");
            System.out.println("<<- Tasks:");
            System.out.println("");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println("<<- Task #" + (i + 1));
                System.out.println(task.toString());
                System.out.println();
            }
        }
    }

    public void markTaskAsCompleted() {
        viewTasks();
        System.out.print("Enter the task number to mark as completed: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (taskNumber >= 1 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.markAsCompleted();
            System.out.println("Task marked as completed: " + task.getTitle());
            System.out.println("");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("Task Reminder Application");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    markTaskAsCompleted();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the Task Reminder Application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        TaskReminderApp app = new TaskReminderApp();
        app.run();
    }
}

