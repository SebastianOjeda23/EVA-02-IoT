package com.example.gestiontareas;

public class Task {
    private String taskName;
    private String taskDescription;
    private boolean isCompleted;

    // Constructor
    public Task(String taskName, String taskDescription, boolean isCompleted) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted; // false para tareas pendientes
    }

    // Getters
    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Setter para actualizar el estado de la tarea
    public void setCompleted(boolean completed) {
        isCompleted = completed; // Cambia el estado de la tarea
    }

    // Métodos para establecer el nombre y la descripción
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}