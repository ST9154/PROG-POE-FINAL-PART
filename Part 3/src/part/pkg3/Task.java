/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package part.pkg3;

/**
 *
 * @author gfff
 */
class Task {
    private String taskName;
    private String taskDescription;
    private String devFirstName;
    private String devLastName;
    private double taskDuration;
    private String taskID;
    private String taskStatus;

    public Task(String taskName, String taskDescription, String devFirstName, String devLastName, double taskDuration, String taskID, String taskStatus) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.devFirstName = devFirstName;
        this.devLastName = devLastName;
        this.taskDuration = taskDuration;
        this.taskID = taskID;
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public String getDevFirstName() {
        return devFirstName;
    }

    public String getDevLastName() {
        return devLastName;
    }

    public double getTaskDuration() {
        return taskDuration;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", devFirstName='" + devFirstName + '\'' +
                ", devLastName='" + devLastName + '\'' +
                ", taskDuration=" + taskDuration +
                ", taskID='" + taskID + '\'' +
                ", taskStatus='" + taskStatus + '\'' +
                '}';
    }
}
