package com.example.taskportfolio.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.taskportfolio.entity.Task;
import com.example.taskportfolio.entity.TaskStatus;
import com.example.taskportfolio.repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<Task> getTasks() {
        return taskRepository.findAllByOrderByIdAsc();
    }

    @Transactional
    public Task createTask(String title, LocalDate dueDate) {
        Task task = new Task(title, dueDate);
        return taskRepository.save(task);
    }

    @Transactional
    public int updateOverdueTasks() {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Tokyo"));
        List<Task> overdueTasks = taskRepository.findByStatusAndDueDateBefore(TaskStatus.TODO, today);
        
        if (overdueTasks.isEmpty()) {
            return 0;
        }
        
        for (Task task : overdueTasks) {
            task.markOverdue();
        }
        
        taskRepository.saveAll(overdueTasks);

        return overdueTasks.size();

    }

}
