package com.example.taskportfolio.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.taskportfolio.dto.TaskResponse;
import com.example.taskportfolio.entity.Task;
import com.example.taskportfolio.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskApiController {
    private final TaskService taskService;

    public TaskApiController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getTasks() {
        List<Task> tasks = taskService.getTasks();
        List<TaskResponse> responses = new ArrayList<>();

        for (Task task : tasks) {
            TaskResponse response = new TaskResponse(
                    task.getId(),
                    task.getTitle(),
                    task.getStatus().name(),
                    task.getDueDate().toString());

            responses.add(response);
        }

        return responses;
    }

}
