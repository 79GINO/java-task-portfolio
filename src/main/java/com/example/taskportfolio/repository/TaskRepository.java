package com.example.taskportfolio.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskportfolio.entity.Task;
import com.example.taskportfolio.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByOrderByIdAsc();

    List<Task> findByStatusAndDueDateBefore(TaskStatus status, LocalDate cutoffDate);

}
