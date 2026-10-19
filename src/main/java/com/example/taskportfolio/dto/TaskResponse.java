package com.example.taskportfolio.dto;

public class TaskResponse {
    private final Long id;
    private final String title;
    private final String status;
    private final String dueDate;

    public TaskResponse(Long id, String title, String status, String dueDate) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getStatus() {
        return status;
    }

    public String getDueDate() {
        return dueDate;
    }

}
