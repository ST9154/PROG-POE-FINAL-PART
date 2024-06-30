/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package part.pkg3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Part3 {
    private static int taskCount = 0;
    private static double totalDuration = 0;
    private static List<String> developers = new ArrayList<>();
    private static List<String> taskNames = new ArrayList<>();
    private static List<String> taskIDs = new ArrayList<>();
    private static List<Double> taskDurations = new ArrayList<>();
    private static List<String> taskStatuses = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to EasyKanban");

        // Login
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        Login login = new Login(username, password);
        boolean isAuthenticated = login.loginUser(username, password);
        if (!isAuthenticated) {
            System.out.println("Login failed. Exiting application.");
            scanner.close();
            return;
        }

        // Task creation
        System.out.println("How many tasks do you wish to enter?");
        int numOfTasks = scanner.nextInt();
        scanner.nextLine(); // consume newline
        Task[] tasks = new Task[numOfTasks];
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println("\nTask " + (i + 1));
            tasks[i] = createTask(scanner);
        }

        // Populate arrays
        populateArrays(tasks);

        // Display tasks
        System.out.println("\nTasks entered:");
        for (Task task : tasks) {
            displayTaskDetails(task);
        }

        // Display total duration
        System.out.println("\nTotal Duration: " + totalDuration + " hours");

        // Additional features
        displayTasksWithStatusDone();
        displayLongestTask();
        searchTaskByName("Add Login Feature");
        searchTasksByDeveloper("John Doe");
        deleteTaskByName("Add Login Feature");
        displayReport(tasks);

        scanner.close();
    }

    public static Task createTask(Scanner scanner) {
        String taskName, taskDescription, devFirstName, devLastName;
        double taskDuration;
        String taskStatus = "";

        while (true) {
            System.out.println("Enter Task Name:");
            taskName = scanner.nextLine();

            System.out.println("Enter Task Description:");
            taskDescription = scanner.nextLine();
            if (taskDescription.length() > 50) {
                System.out.println("Please enter a task description of less than 50 characters");
                continue;
            }

            System.out.println("Enter Developer First Name:");
            devFirstName = scanner.nextLine();
            System.out.println("Enter Developer Last Name:");
            devLastName = scanner.nextLine();
            System.out.println("Enter Task Duration (in hours):");
            taskDuration = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            // Get Task Status
            System.out.println("Select Task Status:");
            System.out.println("1. To Do");
            System.out.println("2. Done");
            System.out.println("3. Doing");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    taskStatus = "To Do";
                    break;
                case 2:
                    taskStatus = "Done";
                    break;
                case 3:
                    taskStatus = "Doing";
                    break;
                default:
                    taskStatus = "To Do";
            }

            // Generate Task ID
            String taskID = generateTaskID(taskName, devLastName);

            // Accumulate total duration
            totalDuration += taskDuration;

            Task task = new Task(taskName, taskDescription, devFirstName, devLastName, taskDuration, taskID, taskStatus);
            taskCount++;
            return task;
        }
    }

    public static String generateTaskID(String taskName, String devLastName) {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskCount + ":" + devLastName.substring(devLastName.length() - 3).toUpperCase();
    }

    public static void displayTaskDetails(Task task) {
        String message = "Task Status: " + task.getTaskStatus() + "\n" +
                "Developer Details: " + task.getDevFirstName() + " " + task.getDevLastName() + "\n" +
                "Task Name: " + task.getTaskName() + "\n" +
                "Task Description: " + task.getTaskDescription() + "\n" +
                "Task ID: " + task.getTaskID() + "\n" +
                "Task Duration: " + task.getTaskDuration() + " hours";
        System.out.println(message); // Use console output for consistency
    }

    public static void populateArrays(Task[] tasks) {
        for (Task task : tasks) {
            developers.add(task.getDevFirstName() + " " + task.getDevLastName());
            taskNames.add(task.getTaskName());
            taskIDs.add(task.getTaskID());
            taskDurations.add(task.getTaskDuration());
            taskStatuses.add(task.getTaskStatus());
        }
    }

    public static void displayTasksWithStatusDone() {
        System.out.println("\nTasks with status 'Done':");
        for (int i = 0; i < taskStatuses.size(); i++) {
            if (taskStatuses.get(i).equals("Done")) {
                System.out.println("Developer: " + developers.get(i) +
                        ", Task Name: " + taskNames.get(i) +
                        ", Task Duration: " + taskDurations.get(i) + " hours");
            }
        }
    }

    public static void displayLongestTask() {
        int longestTaskIndex = 0;
        double longestTaskDuration = 0;
        for (int i = 0; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > longestTaskDuration) {
                longestTaskDuration = taskDurations.get(i);
                longestTaskIndex = i;
            }
        }
        System.out.println("\nLongest Task:");
        System.out.println("Developer: " + developers.get(longestTaskIndex) +
                ", Task Duration: " + longestTaskDuration + " hours");
    }

    public static void searchTaskByName(String name) {
        System.out.println("\nSearch Task by Name:");
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equals(name)) {
                System.out.println("Task Name: " + taskNames.get(i) +
                        ", Developer: " + developers.get(i) +
                        ", Task Status: " + taskStatuses.get(i));
                return;
            }
        }
        System.out.println("Task with name '" + name + "' not found.");
    }

    public static void searchTasksByDeveloper(String developer) {
        System.out.println("\nTasks assigned to " + developer + ":");
        boolean found = false;
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).equals(developer)) {
                System.out.println("Task Name: " + taskNames.get(i) +
                        ", Task Status: " + taskStatuses.get(i));
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks assigned to " + developer);
        }
    }

    public static void deleteTaskByName(String name) {
        System.out.println("\nDeleting Task by Name: " + name);
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equals(name)) {
                developers.remove(i);
                taskNames.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                System.out.println("Task '" + name + "' deleted.");
                return;
            }
        }
        System.out.println("Task with name '" + name + "' not found.");
    }

    public static void displayReport(Task[] tasks) {
        System.out.println("\nReport:");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
